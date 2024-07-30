package com.lspeixotodev.family_activity_control_api.dto.authentication;

import java.util.Objects;

public class UserFullDataDTO {

    private String id;

    private String name;

    private String username;

    private String email;

    private byte[] profilePhoto;

    public UserFullDataDTO() {
    }

    public UserFullDataDTO(String name, String username, String email, byte[] profilePhoto) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.profilePhoto = profilePhoto;
    }

    public UserFullDataDTO(String id, String name, String username, String email, byte[] profilePhoto) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.profilePhoto = profilePhoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFullDataDTO userDTO = (UserFullDataDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
