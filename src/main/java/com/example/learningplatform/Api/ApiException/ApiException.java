package com.example.learningplatform.Api.ApiException;

public class ApiException extends RuntimeException{

    public ApiException(String errMessage){
        super(errMessage);
    }
}
