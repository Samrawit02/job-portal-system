package com.samrit.job.service.impl;

import com.samrit.job.domain.UserRole;
import com.samrit.job.domain.UserStatus;
import com.samrit.job.mapper.UserMapper;
import com.samrit.job.model.User;
import com.samrit.job.payload.AuthResponse;
import com.samrit.job.payload.LoginRequest;
import com.samrit.job.payload.SignupRequest;
import com.samrit.job.repo.UserRepository;
import com.samrit.job.security.CustomUserDetailsService;
import com.samrit.job.security.JwtProvider;
import com.samrit.job.service.AuthService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class  AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    public AuthResponse signup(SignupRequest req) throws Exception {

        if(userRepository.existsByEmail(req.getEmail())){
            throw new Exception("Email Already registered : "+req.getEmail());
        }
        if(req.getRole()== UserRole.ROLE_ADMIN){
            throw new Exception("cannot self-register as role admin");
        }
        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()) )
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        Authentication authentication = new  UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication, savedUser.getId());

        AuthResponse response = new AuthResponse();
        response.setTitle("welcome " + savedUser.getFullName());
        response.setMessage("Registered Successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(savedUser));
        return response;
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
        Authentication authentication = authentication(req.getEmail(), req.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user= userRepository.findByEmail(req.getEmail())
                .orElseThrow(()-> new Exception("User not found"));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse response = new AuthResponse();
        response.setTitle("Welcome back "+ user.getFullName());
        response.setMessage("Login Successfully");
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(user));


        return response;
    }
    private Authentication authentication (String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("Invalid password");
        }
        return  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
