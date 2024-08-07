package com.lspeixotodev.family_activity_control_api.service;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserFullDataDTO;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findUserByUsernameOrEmail(String username, String email);

    UserFullDataDTO findById(String id);

    User findExistingUserById(String id);

}
