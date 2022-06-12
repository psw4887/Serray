package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.ApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError handleException(Exception ex) {

        ApiError apiError = new ApiError();
        apiError.setErrorMessage(ex.getMessage());

        return apiError;
    }
}
