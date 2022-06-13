package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import com.nhnacademy.serrayclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/", "/index"})
public class IndexController {

    private final UserService service;

    @GetMapping
    public String home(Principal principal,
                       Model model) {

        if(Objects.isNull(principal)) {
            model.addAttribute("user", new UserInfoResponse("Guest", "0", "0", "비회원"));
        } else {
            UserInfoResponse response = service.getUser(principal.getName());
            model.addAttribute("user", response);
        }

        return "index";
    }
}
