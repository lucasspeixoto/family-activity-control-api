package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {


    List<ImageDTO> findAll() throws Exception;

    ImageDTO insertProfilePhoto(MultipartFile profilePhoto, String userId) throws IOException;


    ImageDTO findById(String id);

    ImageDTO findByUserId(String userId);
}
