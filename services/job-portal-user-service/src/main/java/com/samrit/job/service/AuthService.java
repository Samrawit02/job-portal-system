package com.samrit.job.service;

import com.samrit.job.payload.AuthResponse;
import com.samrit.job.payload.LoginRequest;
import com.samrit.job.payload.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest req) throws Exception;
    AuthResponse login(LoginRequest req) throws Exception;
}
