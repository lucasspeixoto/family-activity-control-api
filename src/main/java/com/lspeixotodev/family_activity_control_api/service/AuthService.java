package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.authentication.LoginDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.RegisterDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserRegisteredResponse;

public interface AuthService {

    Boolean isUserAdmin(String username);

    JWTAuthResponse login(LoginDTO loginVO);

    JWTAuthResponse refreshToken(String username, String refreshToken);

    UserRegisteredResponse register(RegisterDTO registerDTO);
}
