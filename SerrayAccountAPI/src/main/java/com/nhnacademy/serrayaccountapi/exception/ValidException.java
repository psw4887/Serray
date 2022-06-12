package com.nhnacademy.serrayaccountapi.exception;

import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;

public class ValidException extends RuntimeException {

    public ValidException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
            .stream()
            .map(error -> new StringBuilder()
                .append(error.getDefaultMessage()))
            .collect(Collectors.joining("//")));
    }
}
