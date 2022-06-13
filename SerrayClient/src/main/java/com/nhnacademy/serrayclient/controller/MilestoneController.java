package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.vo.DateForm;
import com.nhnacademy.serrayclient.data.vo.MileForm;
import com.nhnacademy.serrayclient.exception.ValidException;
import com.nhnacademy.serrayclient.service.MilestoneService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
                               @ModelAttribute @Valid MileForm mileForm,
                               BindingResult bindingResult,
                               Principal principal) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.registerMilestone(projectNo, mileForm.getContent(), principal.getName());

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/modify")
    public String readyMileModify(@RequestParam("projectNo") Integer projectNo,
                                 @RequestParam("mileNo") Integer mileNo,
                                 @RequestParam("content") String content,
                                 Model model) {

        model.addAttribute("projectNo", projectNo);
        model.addAttribute("mileNo", mileNo);
        model.addAttribute("content", content);

        return "milestone/milestoneModify";
    }

    @PostMapping("/modify")
    public String mileModify(@RequestParam("projectNo") Integer projectNo,
                             @RequestParam("mileNo") Integer mileNo,
                             @ModelAttribute @Valid MileForm mileForm,
                             BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.modifyMilestone(mileNo, mileForm.getContent());

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
                                   @ModelAttribute @Valid DateForm dateForm,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.addTaskMile(mileNo, taskNo, dateForm.getStart(), dateForm.getEnd());

        return "redirect:/task/detail?taskNo=" + taskNo + "&projectNo=" + projectNo;
    }
}
