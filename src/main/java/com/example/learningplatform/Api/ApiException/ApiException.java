package com.example.learningplatform.Api.ApiException;

public class ApiException extends RuntimeException{

    ApiException(String errMessage){
        super(errMessage);
    }
}