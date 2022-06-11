package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.response.TaskDataResponse;
import com.nhnacademy.serrayclient.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    @GetMapping("/detail")
    public String taskDetail(@RequestParam("taskNo") Integer taskNo,
                             @RequestParam("projectNo") Integer projectNo,
                             Model model) {

        TaskDataResponse taskDataResponse = service.getTaskData(taskNo);

        model.addAttribute("taskNo", taskNo);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("task", taskDataResponse);
        model.addAttribute("lists", taskDataResponse.getComments());
        model.addAttribute("projectTags", taskDataResponse.getTags());
        model.addAttribute("projectMiles",taskDataResponse.getMiles());
        model.addAttribute("taskTags", taskDataResponse.getTaskTags());
        model.addAttribute("taskMile", taskDataResponse.getTaskMile());

        if(Objects.isNull(taskDataResponse.getTaskMile())) {
            model.addAttribute("taskMile", 0);
        }

        return "task/taskDetail";
    }

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

    @GetMapping("/modify")
    public String readyTaskModify(@RequestParam("projectNo") Integer projectNo,
                                  @RequestParam("taskNo") Integer taskNo,
                                  Principal principal,
                                  Model model) {

        TaskDataResponse response = service.getTaskData(taskNo);

        if(!principal.getName().equals(response.getAdmin())) {
            return "redirect:/project/detail/" + projectNo + "?page=0";
        }

        model.addAttribute("task", response);
        model.addAttribute("projectNo", projectNo);

        return "task/taskModifyForm";
    }

    @PostMapping("/modify")
    public String TaskModify(@RequestParam("projectNo") Integer projectNo,
                             @RequestParam("taskNo") Integer taskNo,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content) {

        service.modifyTask(taskNo, title, content);
        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/delete")
    public String taskDelete(@RequestParam("projectNo") Integer projectNo,
                             @RequestParam("taskNo") Integer taskNo,
                             Principal principal) {

        TaskDataResponse response = service.getTaskData(taskNo);

        if(principal.getName().equals(response.getAdmin())) {
            service.deleteTask(taskNo);
        }

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

}
