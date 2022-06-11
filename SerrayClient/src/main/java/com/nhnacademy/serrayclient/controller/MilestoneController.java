package com.nhnacademy.serrayclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mile")
public class MilestoneController {

    @GetMapping("/register")
    public String mileRegister() {
        return null;
    }

    @PostMapping("/modify")
    public String mileModify() {
        return null;
    }

    @PostMapping("/delete")
    public String mileDelete() {
        return null;
    }
}
