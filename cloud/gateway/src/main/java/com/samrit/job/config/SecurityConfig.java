package com.samrit.job.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Authentication is required to access this resource\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"status\":403,\"error\":\"Forbidden\",\"message\":\"Access denied\"}");
                        })
                )
                .authorizeHttpRequests(auth -> auth
                        // Public CORS preflight & Swagger/Actuator endpoints
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/jobs/admin").hasRole("ADMIN")

                        // Public browsing (Reading Jobs, Companies, Categories, Skills, Tags)
                        .requestMatchers(HttpMethod.GET, "/api/jobs/**", "/api/job-categories/**", "/api/job-skills/**", "/api/job-tags/**", "/api/companies/**").permitAll()

                        // Admin-only Endpoints (User Management & Category/Skill administration)
                        .requestMatchers("/api/users/").hasRole("ADMIN")
                        .requestMatchers("/api/users/*/activate", "/api/users/*/suspend").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/job-categories/**", "/api/job-skills/**", "/api/job-tags/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/job-categories/**", "/api/job-skills/**", "/api/job-tags/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/job-categories/**", "/api/job-skills/**", "/api/job-tags/**").hasRole("ADMIN")

                        // Employer Endpoints (Creating & Managing Jobs & Company Profiles)
                        .requestMatchers(HttpMethod.POST, "/api/jobs/**", "/api/companies/**").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/jobs/**", "/api/companies/**").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/jobs/**", "/api/companies/**").hasAnyRole("EMPLOYER", "ADMIN")

                        // Job Seeker Endpoints (Resume & Application Management)
                        .requestMatchers("/api/resumes/**", "/api/resume/**").hasAnyRole("JOB_SEEKER", "ADMIN")

                        // User Profile (Any Authenticated User)
                        .requestMatchers("/api/users/profile").authenticated()

                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenValidator(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(Arrays.asList("Authorization", "X-User-Id", "X-User-Email", "X-User-Roles"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
