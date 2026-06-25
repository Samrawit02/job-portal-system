package com.samrit.job.controller;

import com.samrit.job.payload.AuthResponse;
import com.samrit.job.payload.LoginRequest;
import com.samrit.job.payload.SignupRequest;
import com.samrit.job.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody @Valid SignupRequest request
            ) throws Exception {
        return ResponseEntity.ok(authService.signup(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginRequest request
    ) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }
}
