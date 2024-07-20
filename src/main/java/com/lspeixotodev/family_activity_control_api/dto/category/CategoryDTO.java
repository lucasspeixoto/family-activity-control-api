package com.lspeixotodev.family_activity_control_api.dto.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lspeixotodev.family_activity_control_api.infra.validation.ValidationGroups.Create;
import com.lspeixotodev.family_activity_control_api.infra.validation.annotations.UniqueCategoryTitle;
import com.lspeixotodev.family_activity_control_api.jacoco.ExcludeFromJacocoGeneratedReport;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;


public class CategoryDTO {

    private String id;

    @NotEmpty(message = "The Title is mandatory!")
    @Size(min = 3, message = "The Title must contain at least 3 characters!")
    @UniqueCategoryTitle(message = "This Category title already exists!", groups = Create.class)
    private String title;

    @NotEmpty(message = "The Description is mandatory!")
    @Size(min = 3, message = "The Description must contain at least 3 characters!")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    @JsonIgnore
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    @JsonIgnore
    private Date updatedAt;

    @ExcludeFromJacocoGeneratedReport
    public CategoryDTO() {
    }

    @ExcludeFromJacocoGeneratedReport
    public CategoryDTO(String title, String description, Date createdAt, Date updatedAt) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @ExcludeFromJacocoGeneratedReport
    public CategoryDTO(String id, String title, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
