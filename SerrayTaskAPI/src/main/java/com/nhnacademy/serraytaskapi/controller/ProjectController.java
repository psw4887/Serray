package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.response.ProjectDetailResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.exception.ValidException;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/view/{page}")
    public List<PageableProjectResponse> getPageableProjects(@PathVariable("page") Integer page) {

        return service.getPageableProjectList(page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void projectRegister(@RequestBody @Valid ProjectRegisterVO vo,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        service.registerProject(vo);
    }

    @GetMapping("/detail/{projectNo}/{page}")
    public ProjectDetailResponse getProjectDetail(@PathVariable("projectNo") Integer projectNo,
                                                  @PathVariable("page") Integer page) {

        return service.getDetailProject(page, projectNo);
    }

    @PutMapping("/{projectNo}/states")
    public void modifyProjectState(@PathVariable("projectNo") Integer projectNo,
                                   @RequestParam("state") String state) {

        service.modifyStateProject(projectNo, state);
    }
}
