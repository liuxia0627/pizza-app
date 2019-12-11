package com.jiaren.pizzaapp.types;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Converter
public class JpaToJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper mapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> data) {
        if (null == data) {
            return "{}";
        }
        try {
//            System.out.println(mapper.writeValueAsString(data));
            return mapper.writeValueAsString(data);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        if (null == s) {
            return new HashMap<>();
        }
        try {
            return mapper.readValue(s, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to map", e);
        }
    }
}