package com.nhnacademy.serrayclient.config;

import com.nhnacademy.serrayclient.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    @PostMapping("/register")
    public String memberRegister(@RequestParam("projectNo") Integer projectNo,
                                 @RequestParam("id") String id,
                                 Principal principal) {

        if(service.isProjectAdmin(projectNo, principal.getName())) {
            service.registerMember(projectNo, id);
        }

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }

    @GetMapping("/delete")
    public String memberDelete(@RequestParam("projectNo") Integer projectNo,
                               @RequestParam("id") String id,
                               Principal principal) {

        if(service.isProjectAdmin(projectNo, principal.getName())) {
            service.deleteMember(projectNo, id);
        }

        return "redirect:/project/detail/" + projectNo + "?page=0";
    }
}
