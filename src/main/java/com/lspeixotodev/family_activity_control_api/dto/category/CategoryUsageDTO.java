package com.lspeixotodev.family_activity_control_api.dto.category;

import java.util.Objects;

public class CategoryUsageDTO {

    private String id;

    private String value;

    private String viewValue;

    public CategoryUsageDTO() {}

    public CategoryUsageDTO(String id, String value, String viewValue) {
        this.id = id;
        this.value = value;
        this.viewValue = viewValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getViewValue() {
        return viewValue;
    }

    public void setViewValue(String viewValue) {
        this.viewValue = viewValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryUsageDTO that = (CategoryUsageDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryUsageDTO {" +
                "id: '" + id + '\'' +
                ", value: '" + value + '\'' +
                ", viewValue: '" + viewValue + '\'' +
                '}';
    }
}
