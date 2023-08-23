package com.example.learningplatform.Converter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class ListHashMapConverterTest {

    @Test
    void converterTest() {
        ListHashMapConverter converter = new ListHashMapConverter();

        HashMap<Integer, HashMap<String, String>> methodInput = new HashMap<>(2);

        HashMap<String, String> item1 = new HashMap<>(2) {{
            put("title", "Intro to python");
            put("path", "file1.mp4");
            put("duration", "5:00 minutes");

        }};

        methodInput.put(methodInput.isEmpty() ? 1 : methodInput.size() + 1, item1);
        methodInput.put(methodInput.isEmpty() ? 1 : methodInput.size() + 1, item1);
        methodInput.put(methodInput.isEmpty() ? 1 : methodInput.size() + 1, item1);

        String output = converter.convertToDatabaseColumn(methodInput);
        System.out.println(output);

        HashMap<Integer, HashMap<String, String>> methodInputMatch = converter.convertToEntityAttribute(output);
        String outputMatch = converter.convertToDatabaseColumn(methodInputMatch);
        System.out.printf(outputMatch);


//        assert (methodInput.equals(methodInputMatch));
//        assert (output.equals(outputMatch));


    }

}