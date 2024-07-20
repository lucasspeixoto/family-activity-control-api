package com.lspeixotodev.family_activity_control_api.infra.exceptions;

import com.lspeixotodev.family_activity_control_api.jacoco.ExcludeFromJacocoGeneratedReport;
import com.lspeixotodev.family_activity_control_api.util.constants.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ExcludeFromJacocoGeneratedReport
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String resourceName;

    private final String fieldName;

    private final String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s %s %s %s", resourceName, Messages.NOT_FOUND_MESSAGE, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
