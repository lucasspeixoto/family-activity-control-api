package com.lspeixotodev.family_activity_control_api.dto.category;

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
    public String toString() {
        return "CategoryUsageDTO {" +
                "value: '" + value + '\'' +
                ", viewValue: '" + viewValue + '\'' +
                '}';
    }
}
