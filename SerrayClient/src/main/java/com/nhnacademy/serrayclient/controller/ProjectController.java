package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.ProjectRegisterRequest;
import com.nhnacademy.serrayclient.data.response.*;
import com.nhnacademy.serrayclient.data.vo.ProjectForm;
import com.nhnacademy.serrayclient.exception.ValidException;
import com.nhnacademy.serrayclient.service.MemberService;
import com.nhnacademy.serrayclient.service.ProjectService;
import java.security.Principal;
import java.util.List;

import com.nhnacademy.serrayclient.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final MemberService memberService;

    @GetMapping("/view")
    public String projectListView(@RequestParam("page") Integer page,
                                  Model model) {

        List<ProjectForListResponse> list = projectService.getProjectList(page);

        model.addAttribute("isEnd", 0);

        if(list.size() < 11) {
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
    public String projectRegister(@ModelAttribute @Valid ProjectForm form,
                                  BindingResult bindingResult,
                                  Principal principal) {

        if(bindingResult.hasErrors()) {
            throw new ValidException(bindingResult);
        }

        ProjectRegisterRequest
            request = new ProjectRegisterRequest(principal.getName(), form.getTitle(), form.getContent());

        projectService.registerProject(request);

        return "redirect:/project/view?page=0";
    }

    @GetMapping("/detail/{projectNo}")
    public String projectDetail(@PathVariable("projectNo") Integer projectNo,
                                @RequestParam("page") Integer page,
                                Principal principal,
                                Model model) {

        if(!memberService.isProjectMember(projectNo, principal.getName())) {
            return "redirect:/project/view?page=0";
        }

        ProjectForDetailResponse project = projectService.detailProject(projectNo, page);

        model.addAttribute("project", project);
        model.addAttribute("projectNo", projectNo);
        model.addAttribute("users", userService.getUsersForStateOK());
        model.addAttribute("nowUser", principal.getName());
        model.addAttribute("isEnd", 0);
        
        if(project.getTasks().size() < 6) {
            model.addAttribute("isEnd", 1);
        }

        model.addAttribute("page", page);

        return "project/projectDetail";
    }

    @GetMapping("/state/{projectNo}")
    public String modifyProjectState(@PathVariable("projectNo") Integer projectNo,
                                     @RequestParam("state") String state,
                                     Principal principal) {

        if(memberService.isProjectAdmin(projectNo, principal.getName())) {
            projectService.projectModifyState(projectNo, state);
        }

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }
}
