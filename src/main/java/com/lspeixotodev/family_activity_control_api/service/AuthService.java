package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.authentication.LoginDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.RegisterDTO;
import com.lspeixotodev.family_activity_control_api.dto.response.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.dto.response.SuccessRegisterResponse;
import jakarta.validation.Valid;


public interface AuthService {
    JWTAuthResponse login(@Valid LoginDTO loginVO);

    JWTAuthResponse refreshToken(String username, String refreshToken);

    SuccessRegisterResponse register(@Valid RegisterDTO registerVO);
}
