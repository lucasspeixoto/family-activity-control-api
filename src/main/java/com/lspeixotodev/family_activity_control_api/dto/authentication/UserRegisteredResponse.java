package com.lspeixotodev.family_activity_control_api.dto.authentication;

import java.util.Objects;

public class UserRegisteredResponse {
    
    private String message;
    
    private String detail;

    public UserRegisteredResponse() {
    }

    public UserRegisteredResponse(String message, String detail) {
        this.message = message;
        this.detail = detail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisteredResponse that = (UserRegisteredResponse) o;
        return Objects.equals(message, that.message) && Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, detail);
    }
}
