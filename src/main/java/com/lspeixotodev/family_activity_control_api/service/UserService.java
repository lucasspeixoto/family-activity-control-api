package com.lspeixotodev.family_activity_control_api.service;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserFullDataDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUsernameOrEmail(String username, String email);

    List<UserDTO> findAll();

    UserFullDataDTO findById(String id);

}
