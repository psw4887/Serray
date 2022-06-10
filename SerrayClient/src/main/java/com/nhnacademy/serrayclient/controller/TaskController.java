package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping("/register")
    public String readyTaskRegister(@RequestParam("projectNo") Integer projectNo,
                                    Model model) {

        model.addAttribute("projectNo", projectNo);
        return "task/taskRegisterForm";
    }

    @PostMapping("/register")
    public String taskRegister(@RequestParam("projectNo") Integer projectNo,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content,
                               Principal principal) {

        service.registerTask(projectNo, principal.getName(), title, content);
        return "redirect:/project/detail/" + projectNo + "?page=0";
    }
}
