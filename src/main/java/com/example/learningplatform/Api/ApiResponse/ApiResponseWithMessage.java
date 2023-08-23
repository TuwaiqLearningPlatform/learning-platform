package com.example.learningplatform.Api.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWithMessage<T> {
    private String message;
    private T response;

 }