package com.example.learningplatform.Converter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class ListHashMapConverterTest {

    @Test
    void converterTest() {
        ListHashMapConverter converter = new ListHashMapConverter();

        List<HashMap<String, Object>> methodInput = new ArrayList<>(2);

        HashMap<String, Object> item1 = new HashMap<>(2) {{
            put("A","file1.mp4");
            put("B", "file2.mp4");
        }};

        methodInput.add(item1);
        methodInput.add(item1);

        String output = converter.convertToDatabaseColumn(methodInput);
        System.out.println(output);

        List<HashMap<String, Object>> methodInputMatch = converter.convertToEntityAttribute(output);
        String outputMatch = converter.convertToDatabaseColumn(methodInputMatch);
        System.out.printf(outputMatch);


        assert (methodInput.equals(methodInputMatch));
        assert (output.equals(outputMatch));


    }

}