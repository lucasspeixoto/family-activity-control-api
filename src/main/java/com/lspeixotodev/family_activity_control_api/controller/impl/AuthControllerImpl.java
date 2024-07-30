package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.AuthController;
import com.lspeixotodev.family_activity_control_api.dto.authentication.LoginDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.RegisterDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserRegisteredResponse;
import com.lspeixotodev.family_activity_control_api.service.impl.AuthServiceImpl;
import com.lspeixotodev.family_activity_control_api.util.constants.MediaType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON)
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Override
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginDTO loginVO) {
        return new ResponseEntity<>(authService.login(loginVO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserRegisteredResponse> register(
            @Valid @RequestBody RegisterDTO registerDTO

    ) {
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JWTAuthResponse> refreshToken(
            @PathVariable("username") String username,
            @RequestHeader("Authorization") String refreshToken
    ) {
        return new ResponseEntity<>(authService.refreshToken(username, refreshToken), HttpStatus.OK);
    }
}
