package com.nhnacademy.serrayaccountapi.controller;

import com.nhnacademy.serrayaccountapi.data.response.OnlyUserIdResponse;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;
import com.nhnacademy.serrayaccountapi.exception.ValidException;
import com.nhnacademy.serrayaccountapi.service.UserService;
import javax.validation.Valid;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<OnlyUserIdResponse> getStateOKUsers() {

        return service.getUserListStateOK();
    }

    @GetMapping("/get/{id}")
    public ForLoginUserVO getUserById(@PathVariable("id") String id) {

        return service.findUserById(id);
    }

    @GetMapping("/get/git")
    public ForLoginUserVO getUserByEmail(@RequestParam("email") String email) {

        return service.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void RegisterUser(@RequestBody @Valid UserRegisterResponse response,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.userRegister(response);
    }

    @PutMapping("/modify/{user}/{state}")
    public void modifyUserState(@PathVariable("user") String user,
                                @PathVariable("state") String state) {

        service.userStateModify(user, state);
    }
}
