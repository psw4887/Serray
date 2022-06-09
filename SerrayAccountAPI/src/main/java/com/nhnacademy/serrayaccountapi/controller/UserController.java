package com.nhnacademy.serrayaccountapi.controller;

import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void RegisterUser(@RequestBody UserRegisterResponse response) {

        service.userRegister(response);
    }
}
