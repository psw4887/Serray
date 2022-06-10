package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.PostRegisterRequest;
import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import com.nhnacademy.serrayclient.service.ProjectService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/view")
    public String projectListView(@RequestParam("page") Integer page,
                                  Model model) {

        List<ProjectForListResponse> list = service.getProjectList(page);

        model.addAttribute("isEnd", 0);

        if(list.size() < 10) {
            model.addAttribute("isEnd", 1);
        }

        model.addAttribute("lists", list);
        model.addAttribute("page", page);

        return "project/projectList";
    }

    @GetMapping("/register")
    public String readyProjectRegister() {

        return "project/projectRegisterForm";
    }

    @PostMapping("/register")
    public String projectRegister(@RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  Principal principal) {

        PostRegisterRequest request = new PostRegisterRequest(principal.getName(), title, content);

        service.registerProject(request);

        return "redirect:/project/view?page=0";
    }
}
