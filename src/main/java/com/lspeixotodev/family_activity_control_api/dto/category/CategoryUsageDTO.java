package com.lspeixotodev.family_activity_control_api.dto.category;

import com.lspeixotodev.family_activity_control_api.jacoco.ExcludeFromJacocoGeneratedReport;

import java.util.Objects;

public class CategoryUsageDTO {

    private String value;

    private String viewValue;

    public CategoryUsageDTO() {}

    public CategoryUsageDTO(String value, String viewValue) {
        this.value = value;
        this.viewValue = viewValue;
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
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryUsageDTO that = (CategoryUsageDTO) o;
        return Objects.equals(value, that.value) && Objects.equals(viewValue, that.viewValue);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return Objects.hash(value, viewValue);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
        return "CategoryUsageDTO {" +
                "value: '" + value + '\'' +
                ", viewValue: '" + viewValue + '\'' +
                '}';
    }
}
