package com.lspeixotodev.family_activity_control_api.util.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToCapitalizeCaseConverter extends StdConverter<String, String> {
    @Override
    public String convert(String value) {
        Stream<String> words = Arrays.stream(value.split(" "));

        Stream<String> capitalizedWords = words.map(word -> word.substring(0, 1)
                .toUpperCase() + word.substring(1).toLowerCase());

        return capitalizedWords.collect(Collectors.joining(" "));
    }
}
