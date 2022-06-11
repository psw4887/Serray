package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mile")
public class MilestoneController {

    private final MilestoneService service;

    @PostMapping("/register")
    public String mileRegister(@RequestParam("projectNo") Integer projectNo,
                              @RequestParam("content") String content,
                              Principal principal) {

        service.registerMilestone(projectNo, content, principal.getName());

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/modify")
    public String mileModify(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("mileNo") Integer mileNo,
                            @RequestParam("content") String content) {

        service.modifyMilestone(mileNo, content);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/delete")
    public String mileDelete(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("mileNo") Integer mileNo) {

        service.deleteMilestone(mileNo);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }
}
