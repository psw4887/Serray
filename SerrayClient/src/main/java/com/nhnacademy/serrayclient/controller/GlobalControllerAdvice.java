package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.ApiError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiError handleException(Exception ex) {

        ApiError apiError = new ApiError();
        apiError.setErrorMessage(ex.getMessage());

        return apiError;
    }
}
