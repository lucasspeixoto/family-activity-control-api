package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserFullDataDTO;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class MockUser {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public User getFirstUser() {
        return mockFirstUser();
    }

    public User getSecondUser() {
        return mockSecondUser();
    }

    public UserDTO getFirstUserDTO() {
        return mockUserDTO();
    }

    public UserFullDataDTO getUserFullDataDTO() {
        return mockUserFullDataDTO();
    }


    private User mockFirstUser() {
        User user = new User();
        user.setId(UUID.fromString("fd361c69-4657-44da-917f-0ee7c00e491d"));
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lucas");
        user.setPassword("password123");

        return user;
    }

    private User mockSecondUser() {
        User user = new User();
        user.setId(UUID.fromString("b4c8d28e-a537-4a89-86de-171da4c56c5f"));
        user.setName("Admin");
        user.setEmail("admin@email.com");
        user.setUsername("admin");
        user.setPassword("admin");

        return user;
    }

    private UserFullDataDTO mockUserFullDataDTO() {
        UserFullDataDTO user = new UserFullDataDTO();
        user.setId("b4c8d28e-a537-4a89-86de-171da4c56c5f");
        user.setName("Admin");
        user.setEmail("admin@email.com");
        user.setUsername("admin");
        user.setProfilePhoto(null);

        return user;
    }

    private UserDTO mockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId("b4c8d28e-a537-4a89-86de-171da4c56c5f");
        userDTO.setName("Admin");
        userDTO.setEmail("admin@email.com");
        userDTO.setUsername("admin");

        return userDTO;
    }
}
