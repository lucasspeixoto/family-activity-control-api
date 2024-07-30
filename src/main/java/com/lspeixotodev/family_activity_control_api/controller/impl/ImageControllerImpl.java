package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.ImageController;
import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import com.lspeixotodev.family_activity_control_api.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/images")
public class ImageControllerImpl implements ImageController {

    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @Override
    public ResponseEntity<List<ImageDTO>> findAll() throws Exception {

        return ResponseEntity.ok(imageServiceImpl.findAll());
    }

    @Override
    public ResponseEntity<ImageDTO> insertProfilePhoto(
            @RequestParam("file") MultipartFile profilePhoto, @PathVariable String userId
    ) throws IOException {
        return ResponseEntity.ok().body(imageServiceImpl.insertProfilePhoto(profilePhoto, userId));
    }

    @Override
    public ResponseEntity<ImageDTO> findById(@PathVariable String id) {
        ImageDTO selectedImage = this.imageServiceImpl.findById(id);

        return ResponseEntity.ok().body(selectedImage);
    }

    @Override
    public ResponseEntity<ImageDTO> findByUserId(@RequestParam(value = "userId") String id) throws Exception {
        return ResponseEntity.ok(this.imageServiceImpl.findByUserId(id));
    }
}
