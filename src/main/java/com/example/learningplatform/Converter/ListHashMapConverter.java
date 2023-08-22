package com.example.learningplatform.Converter;

import com.example.learningplatform.Api.ApiException.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// this will automatically serialize and deserialize the content field.
public class ListHashMapConverter implements AttributeConverter<List<HashMap<String, Object>>, String> {

    private final ObjectMapper objectMapper  =  new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<HashMap<String, Object>> contentList) {

        String content = null;
        try {
            content = objectMapper.writeValueAsString(contentList);
        } catch (final JsonProcessingException e) {
            throw new ApiException("JSON writing error");
        }

        return content;
    }


    @Override
    public List<HashMap<String, Object>> convertToEntityAttribute(String content) {

        List<HashMap<String, Object>> contentList = null;
        try {
            contentList = objectMapper.readValue(content,
                    new TypeReference<List<HashMap<String, Object>>>() {}
            );
        } catch (final IOException e) {
            throw new ApiException("JSON writing error");
        }

        return contentList;
    }

}
