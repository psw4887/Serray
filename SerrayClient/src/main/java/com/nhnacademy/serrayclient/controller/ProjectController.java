package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.ProjectRegisterRequest;
import com.nhnacademy.serrayclient.data.response.ProjectForDetailResponse;
import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import com.nhnacademy.serrayclient.service.ProjectService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/register")
    public String projectRegister(@RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  Principal principal) {

        ProjectRegisterRequest
            request = new ProjectRegisterRequest(principal.getName(), title, content);

        service.registerProject(request);

        return "redirect:/project/view?page=0";
    }

    @GetMapping("/detail/{projectNo}")
    public String projectDetail(@PathVariable("projectNo") Integer projectNo,
                                Model model) {

        ProjectForDetailResponse project = service.detailProject(projectNo);

        model.addAttribute("project", project);
        model.addAttribute("projectNo", projectNo);

        return "project/projectDetail";
    }
}
