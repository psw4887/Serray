package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.ProjectRegisterRequest;
import com.nhnacademy.serrayclient.data.response.*;
import com.nhnacademy.serrayclient.service.MemberService;
import com.nhnacademy.serrayclient.service.ProjectService;
import java.security.Principal;
import java.util.List;

import com.nhnacademy.serrayclient.service.UserService;
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
    public String projectRegister(@RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  Principal principal) {

        ProjectRegisterRequest
            request = new ProjectRegisterRequest(principal.getName(), title, content);

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

        List<ProjectForDetailTaskResponse> taskList = project.getTasks();
        List<ProjectForDetailMemberResponse> memberList = project.getMembers();
        List<UserIdResponse> userList = userService.getUsersForStateOK();

        model.addAttribute("lists", taskList);
        model.addAttribute("members", memberList);
        model.addAttribute("users", userList);
        model.addAttribute("isEnd", 0);


        if(taskList.size() < 6) {
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
