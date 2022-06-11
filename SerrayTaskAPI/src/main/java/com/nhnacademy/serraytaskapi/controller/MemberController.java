package com.nhnacademy.serraytaskapi.controller;

import com.nhnacademy.serraytaskapi.entity.Member;
import com.nhnacademy.serraytaskapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    @GetMapping("/admin")
    public boolean isProjectAdmin(@RequestParam("projectNo") Integer projectNo,
                                  @RequestParam("id") String id) {

        return service.isAdmin(projectNo, id);
    }

    @GetMapping("/member")
    public boolean isProjectMember(@RequestParam("projectNo") Integer projectNo,
                                   @RequestParam("id") String id) {

        return service.isMember(projectNo, id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void memberRegister(@RequestParam("projectNo") Integer projectNo,
                                  @RequestParam("id") String id) {

        service.registerMember(projectNo, id);
    }

    @DeleteMapping("/delete")
    public void memberDelete(@RequestParam("projectNo") Integer projectNo,
                                @RequestParam("id") String id) {

        service.deleteMember(projectNo, id);
    }
}
