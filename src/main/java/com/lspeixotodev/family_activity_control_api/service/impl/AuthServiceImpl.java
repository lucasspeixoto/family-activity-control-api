package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.authentication.LoginDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.RegisterDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.JWTAuthResponse;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserRegisteredResponse;
import com.lspeixotodev.family_activity_control_api.entity.authentication.Role;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.PlatformException;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.repository.authentication.RoleRepository;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import com.lspeixotodev.family_activity_control_api.security.jwt.JwtTokenProvider;
import com.lspeixotodev.family_activity_control_api.service.AuthService;
import com.lspeixotodev.family_activity_control_api.util.constants.Messages;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JWTAuthResponse login(@Valid LoginDTO loginDTO) {
        logger.info("Start logging user at: {}", LocalDateTime.now());

        String username = loginDTO.getUsernameOrEmail();
        String password = loginDTO.getPassword();

        if (this.userRepository.findByUsernameOrEmail(username, username).isEmpty()) {
            throw new ResourceNotFoundException("User", "user/email", username);
        }

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username,
                                password
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(username);
    }

    public JWTAuthResponse refreshToken(String username, String refreshToken) {

        var tokenResponse = new JWTAuthResponse();

        if (this.userRepository.findByUsernameOrEmail(username, username).isEmpty()) {
            throw new ResourceNotFoundException("User", "user", username);
        }

        tokenResponse = jwtTokenProvider.generateRefreshedToken(refreshToken);

        return tokenResponse;
    }

    public UserRegisteredResponse register(RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_USERNAME_MESSAGE);
        }

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_EMAIL_MESSAGE);
        }

        User user = new User();

        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (roleRepository.findByName("ROLE_USER").isPresent()) {
            Role userRole = roleRepository.findByName("ROLE_USER").get();

            roles.add(userRole);
            user.setRoles(roles);

            this.userRepository.save(user);

            return new UserRegisteredResponse(
                    Messages.REGISTER_SUCCESS_MESSAGE,
                    Messages.REGISTER_SUCCESS_DETAIL
            );

        } else {
            throw new PlatformException(HttpStatus.UNAUTHORIZED, Messages.PERMISSION_NOT_FOUND);
        }
    }

}