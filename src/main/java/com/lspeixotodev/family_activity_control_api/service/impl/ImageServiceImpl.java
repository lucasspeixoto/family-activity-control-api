package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import com.lspeixotodev.family_activity_control_api.entity.Image;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.ImageMapper;
import com.lspeixotodev.family_activity_control_api.repository.ImageRepository;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import com.lspeixotodev.family_activity_control_api.service.ImageService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<ImageDTO> findAll() throws Exception {

        logger.info("Finding all images!");

        List<Image> images = this.imageRepository.findAll();

        return this.imageMapper.entitiesToDtos(images);

    }

    @Transactional
    @Override
    public ImageDTO insertProfilePhoto(MultipartFile profilePhoto, String userId) throws IOException {

        // Check existing user
        Optional<User> user = this.userRepository.findById(UUID.fromString(userId));

        if (user.isEmpty()) {
            logger.info("User does not existing!");
            throw new ResourceNotFoundException("User", "id", userId);
        }

        // Check existing user profile photo
        Optional<Image> optionalUserImage = this.imageRepository.findByUserId(UUID.fromString(userId));

        if (optionalUserImage.isEmpty()) {
            // If user does not have a profile photo yet
            logger.info("Uploading new user profile photo!");

            Image newUserImage = new Image();

            newUserImage.setUser(user.get());

            newUserImage.setProfilePhoto(profilePhoto.getBytes());

            Image savedImage = this.imageRepository.save(newUserImage);

            return this.imageMapper.entityToDto(savedImage);

        } else {
            // If user already have a profile photo
            logger.info("Updating a user profile photo!");

            Image existingUserImage = optionalUserImage.get();

            existingUserImage.setProfilePhoto(profilePhoto.getBytes());

            Image savedImage = this.imageRepository.save(existingUserImage);

            return this.imageMapper.entityToDto(savedImage);
        }
    }

    @Override
    public ImageDTO findById(String id) {
        logger.info("Finding a image by Id");

        Optional<Image> optionalImage = this.imageRepository.findById(UUID.fromString(id));

        if (optionalImage.isEmpty()) {
            throw new ResourceNotFoundException("Image", "id", id);
        }

        return this.imageMapper.entityToDto(optionalImage.get());

    }

    @Override
    public ImageDTO findByUserId(String userId) {
        logger.info("Finding a image by user id");

        Optional<Image> optionalImage = this.imageRepository.findByUserId(UUID.fromString(userId));

        if (optionalImage.isEmpty()) {
            throw new ResourceNotFoundException("Image", "user id", userId);
        }

        return this.imageMapper.entityToDto(optionalImage.get());
    }
}
