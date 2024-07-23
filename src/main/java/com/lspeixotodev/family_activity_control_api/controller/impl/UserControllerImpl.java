package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.UserController;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserServiceImpl userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDTO> getUserData(@PathVariable(value = "usernameOrEmail") String usernameOrEmail) {
        UserDTO userDTO = this.userService.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = this.userService.findAll();

        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") String id) {
        UserDTO userVO = this.userService.findById(id);

        return ResponseEntity.ok(userVO);
    }
}
