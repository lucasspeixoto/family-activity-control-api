package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.ImageDTO;
import com.lspeixotodev.family_activity_control_api.entity.Image;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class MockImage {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Image getImage() throws ParseException {
        return mockImage();
    }

    public ImageDTO getImageDTO() throws ParseException {
        return mockImageDTO();
    }

    private Image mockImage() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Image image = new Image();
        image.setId(UUID.fromString("b604314f-985f-48f1-85a1-3f6692b18d93"));
        image.setProfilePhoto(null);
        image.setCreatedAt(simpleDateFormat.parse("30/10/1991"));
        image.setUpdatedAt(simpleDateFormat.parse("30/10/1991"));
        image.setUser(mockImageUser());

        return image;
    }

    private ImageDTO mockImageDTO() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ImageDTO image = new ImageDTO();
        image.setId("b604314f-985f-48f1-85a1-3f6692b18d93");
        image.setProfilePhoto(null);
        image.setCreatedAt(simpleDateFormat.parse("30/10/1991"));
        image.setUpdatedAt(simpleDateFormat.parse("30/10/1991"));
        image.setUser(mockImageUser());

        return image;
    }

    private User mockImageUser() {
        User user = new User();
        user.setId(UUID.fromString("fd361c69-4657-44da-917f-0ee7c00e491d"));
        user.setName("Lucas Peixoto");
        user.setEmail("lspeixotodev@gmail.com");
        user.setUsername("lspeixotodev");
        user.setPassword("password123");

        return user;
    }
}
