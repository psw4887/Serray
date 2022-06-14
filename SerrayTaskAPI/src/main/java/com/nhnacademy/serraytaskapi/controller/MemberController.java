package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectNo}")
public class MemberController {

    private final MemberService service;

    @GetMapping("auths/admins")
    public boolean isProjectAdmin(@PathVariable("projectNo") Integer projectNo,
                                  @RequestParam("id")  String id) {

        return service.isAdmin(projectNo, id);
    }

    @GetMapping("auths/members")
    public boolean isProjectMember(@PathVariable("projectNo") Integer projectNo,
                                   @RequestParam("id") String id) {

        return service.isMember(projectNo, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/members/register")
    public void memberRegister(@PathVariable("projectNo") Integer projectNo,
                               @RequestParam("id") String id) {

        service.registerMember(projectNo, id);
    }

    @DeleteMapping("/members/delete")
    public void memberDelete(@RequestParam("projectNo") Integer projectNo,
                             @RequestParam("id") String id) {

        service.deleteMember(projectNo, id);
    }
}
