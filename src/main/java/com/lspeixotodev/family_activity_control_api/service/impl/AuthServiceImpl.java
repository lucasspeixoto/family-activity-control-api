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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

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

    public JWTAuthResponse login(@Valid LoginDTO loginVO) {

        String username = loginVO.getUsernameOrEmail();
        String password = loginVO.getPassword();

        if (!userRepository.existsByUsername(username)) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.LOGIN_EMAIL_PASSWORD_MESSAGE);
        }

        this.userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User ", "user", username)
                );

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

        this.userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User ", "user", username)
                );

        tokenResponse = jwtTokenProvider.generateRefreshedToken(refreshToken);

        return tokenResponse;
    }

    public UserRegisteredResponse register(@Valid RegisterDTO registerVO) {

        if (userRepository.existsByUsername(registerVO.getUsername())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_USERNAME_MESSAGE);
        }

        if (userRepository.existsByEmail(registerVO.getEmail())) {
            throw new PlatformException(HttpStatus.FORBIDDEN, Messages.REGISTER_ALREADY_EXISTS_EMAIL_MESSAGE);
        }

        User user = new User();

        user.setName(registerVO.getName());
        user.setEmail(registerVO.getEmail());
        user.setUsername(registerVO.getUsername());
        user.setPassword(passwordEncoder.encode(registerVO.getPassword()));

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
            throw new PlatformException(HttpStatus.CREATED, Messages.PERMISSION_NOT_FOUND);
        }
    }

}