package com.lspeixotodev.family_activity_control_api.util.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ToUpperCaseConverter extends StdConverter<String, String> {
    @Override
    public String convert(String value) {
        return value.toUpperCase();
    }
}
