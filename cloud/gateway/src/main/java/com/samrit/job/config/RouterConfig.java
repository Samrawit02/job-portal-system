package com.samrit.job.config;


import com.samrit.job.jwt.JwtConstant;
import com.samrit.job.jwt.JwtUtils;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterConfig {

    private final JwtUtils jwtUtils;

    public RouterConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public RouterFunction<ServerResponse> authRoute(){
        return GatewayRouterFunctions.route("auth-routes")
                .route(RequestPredicates.path("/auth/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> adminRoute(){
        return GatewayRouterFunctions.route("admin-routes")
                .route(RequestPredicates.path("/api/admin/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> userServiceRoute(){
        return GatewayRouterFunctions.route("user-service-routes")
                .route(RequestPredicates.path("/api/user/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-user-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> companyServiceRoute(){
        return GatewayRouterFunctions.route("company-service-routes")
                .route(RequestPredicates.path("/api/companies/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-company-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> applicationServiceRoute(){
        return GatewayRouterFunctions.route("application-service-routes")
                .route(RequestPredicates.path("/api/applications/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-application-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> jobServiceRoute(){
        return GatewayRouterFunctions.route("job-service-routes")
                .route(RequestPredicates.path("/api/jobs/**")
                                .or(RequestPredicates.path("/api/job-categories/**"))
                                .or(RequestPredicates.path("/api/job-skills/**"))
                                .or(RequestPredicates.path("/api/job-tags/**"))
                        , HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-job-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> resumeServiceRoute(){
        return GatewayRouterFunctions.route("resume-service-routes")
                .route(RequestPredicates.path("/api/resumes/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("job-portal-company-service"))
                .before(this::jwtAuthFilter)
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> preferenceServiceRoute(){
        return GatewayRouterFunctions.route("preference-service-routes")
                .route(RequestPredicates.path("/api/preferences/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("preferences-portal-company-service"))
                .before(this::jwtAuthFilter)
                .build();
    }

    private ServerRequest jwtAuthFilter(ServerRequest request){
        String authHeader = request.headers().firstHeader(JwtConstant.JWT_HEADER);

        if (authHeader == null || !authHeader.startsWith(JwtConstant.TOKEN_PREFIX)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Missing or invalid authorization header");
        }

//        if(authHeader !=null || !authHeader.startsWith(JwtConstant.TOKEN_PREFIX)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
//                    "missing or invalid authorization header");
//        }
        String token=authHeader.substring(JwtConstant.TOKEN_PREFIX.length());
        if(!jwtUtils.isTokenValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid or expired Jwt token");
        }
        String email = jwtUtils.extractEmail(token);
        String authorities= jwtUtils.extractAuthorities(token);
        Long userId = jwtUtils.extractUserId(token);

        return ServerRequest.from(request)
                .header("X-User-Id", String.valueOf(userId))
                .header("X-User-Email", email)
                .header("X-User-Role", authorities)
                .build();



    }


}
