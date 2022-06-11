package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

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

    @GetMapping("/modify")
    public String readyTagModify(@RequestParam("projectNo") Integer projectNo,
                                 @RequestParam("mileNo") Integer mileNo,
                                 @RequestParam("content") String content,
                                 Model model) {

        model.addAttribute("projectNo", projectNo);
        model.addAttribute("mileNo", mileNo);
        model.addAttribute("content", content);

        return "/milestone/milestoneModify";
    }

    @PostMapping("/modify")
    public String mileModify(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("mileNo") Integer mileNo,
                            @RequestParam("content") String content) {

        service.modifyMilestone(mileNo, content);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/delete")
    public String mileDelete(@RequestParam("projectNo") Integer projectNo,
                            @RequestParam("mileNo") Integer mileNo) {

        service.deleteMilestone(mileNo);

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @PostMapping("/task/register")
    public String taskMileRegister(@RequestParam("projectNo") Integer projectNo,
                                  @RequestParam("taskNo") Integer taskNo,
                                  @RequestParam("mileNo") Integer mileNo,
                                  @RequestParam("start")
                                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                  @RequestParam("end")
                                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end) {

        service.addTaskMile(mileNo, taskNo, start, end);

        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }
}
