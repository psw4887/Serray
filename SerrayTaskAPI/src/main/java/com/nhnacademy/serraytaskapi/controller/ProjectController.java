package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.response.ProjectDetailResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/view/{page}")
    public List<PageableProjectResponse> getPageableProjects(@PathVariable("page") Integer page) {

        return service.getPageableProjectList(page);
    }

    @PostMapping("/register")
    public void projectRegister(@RequestBody ProjectRegisterVO vo) {

        service.registerProject(vo);
    }

    @GetMapping("/detail/{projectNo}/{page}")
    public ProjectDetailResponse getProjectDetail(@PathVariable("projectNo") Integer projectNo,
                                                  @PathVariable("page") Integer page) {

        return service.getDetailProject(page, projectNo);
    }
}
