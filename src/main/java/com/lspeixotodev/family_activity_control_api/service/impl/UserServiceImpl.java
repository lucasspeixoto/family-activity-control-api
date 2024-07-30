package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.authentication.UserDTO;
import com.lspeixotodev.family_activity_control_api.dto.authentication.UserFullDataDTO;
import com.lspeixotodev.family_activity_control_api.entity.Image;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.authentication.UserMapper;
import com.lspeixotodev.family_activity_control_api.repository.ImageRepository;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import com.lspeixotodev.family_activity_control_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public UserDTO findByUsernameOrEmail(String username, String email) {
        User user = this.repository.findByUsernameOrEmail(username, email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email)
        );

        return this.userMapper.entityToDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = this.repository.findAll();

        return this.userMapper.entitiesToDtos(users);
    }

    @Override
    public UserFullDataDTO findById(String id) {

        UUID uuid = UUID.fromString(id);

        User user = this.repository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        UserFullDataDTO userDTO = this.userMapper.entityToFullDataDto(user);

        Optional<Image> optionalImage = this.imageRepository.findByUserId(UUID.fromString(id));

        optionalImage.ifPresent(image -> userDTO.setProfilePhoto(image.getProfilePhoto()));

        return userDTO;
    }
}
