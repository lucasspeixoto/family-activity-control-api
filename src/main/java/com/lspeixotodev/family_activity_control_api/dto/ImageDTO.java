package com.lspeixotodev.family_activity_control_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lspeixotodev.family_activity_control_api.entity.authentication.User;

import java.util.Date;
import java.util.Objects;

public class ImageDTO {

    private String id;

    private byte[] profilePhoto;

    @JsonIgnore
    private Date updatedAt;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private User user;

    public ImageDTO() {
    }

    public ImageDTO(String id, byte[] profilePhoto, Date updatedAt, Date createdAt, User user) {
        this.id = id;
        this.profilePhoto = profilePhoto;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return Objects.equals(id, imageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
