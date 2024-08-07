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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<UserDTO> findAll() {
        logger.info("Start finding all users at: {}", LocalDateTime.now());
        List<User> users = this.repository.findAll();

        return this.userMapper.entitiesToDtos(users);
    }

    @Override
    public UserDTO findUserByUsernameOrEmail(String username, String email) {
        logger.info("Start finding a user by username or email at: {}", LocalDateTime.now());

        Optional<User> optionalUser = this.repository.findUserByUsernameOrEmail(username, email);

        if (optionalUser.isEmpty()) {
            logger.info("User not found for by username or email for this username or email");
            throw new ResourceNotFoundException("User", "username/email", username);
        }

        logger.info("Found user by username or email at: {}", LocalDateTime.now());
        return this.userMapper.entityToDto(optionalUser.get());
    }

    @Override
    public UserFullDataDTO findById(String id) {
        logger.info("Start finding a user by id at: {}", LocalDateTime.now());
        UUID uuid = UUID.fromString(id);

        Optional<User> optionalUser = this.repository.findById(uuid);

        if (optionalUser.isEmpty()) {
            logger.info("User not found by Id for this username or email");
            throw new ResourceNotFoundException("User", "id", id);
        }

        UserFullDataDTO userDTO = this.userMapper.entityToFullDataDto(optionalUser.get());

        Optional<Image> optionalImage = this.imageRepository.findByUserId(UUID.fromString(id));

        optionalImage.ifPresent(image -> userDTO.setProfilePhoto(image.getProfilePhoto()));

        return userDTO;
    }

    @Override
    public User findExistingUserById(String id) {
        logger.info("Start finding a existing user by id at: {}", LocalDateTime.now());
        UUID uuid = UUID.fromString(id);

        Optional<User> optionalUser = this.repository.findById(uuid);

        if (optionalUser.isEmpty()) {
            logger.info("User not found by existing Id for this id");
            throw new ResourceNotFoundException("User", "id", id);
        }

        return optionalUser.get();
    }
}
