package com.nhnacademy.serrayclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mile")
public class MilestoneController {

    @GetMapping("/register")
    public String mileRegister(@RequestParam("projectNo") Integer projectNo) {

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/modify")
    public String mileModify(@RequestParam("projectNo") Integer projectNo) {

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/delete")
    public String mileDelete(@RequestParam("projectNo") Integer projectNo) {

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }
}
