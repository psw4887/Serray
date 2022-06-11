package com.nhnacademy.serrayclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    @GetMapping("/register")
    public String tagRegister() {
        return null;
    }

    @PostMapping("/modify")
    public String tagModify() {
        return null;
    }

    @PostMapping("/delete")
    public String tagDelete() {
        return null;
    }
}
