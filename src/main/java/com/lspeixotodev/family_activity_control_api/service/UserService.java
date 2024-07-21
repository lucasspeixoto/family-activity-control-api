package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;

import java.util.List;


public interface UserService {

    UserDTO findByUsernameOrEmail(String username, String email);

    List<UserDTO> findAll();

    UserDTO findById(String id);
}
