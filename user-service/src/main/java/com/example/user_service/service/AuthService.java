package com.example.user_service.service;

import com.example.user_service.dto.AuthResponse;
import com.example.user_service.dto.LoginRequest;
import com.example.user_service.dto.RegisterRequest;

public interface AuthService {

     AuthResponse register(RegisterRequest request);
     AuthResponse login(LoginRequest request);
}
