package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {

    private final UserService service;

    @GetMapping
    public String readyJoin() {

        return "Join";
    }

    @PostMapping
    public String doJoin(@RequestParam("id") String id,
                         @RequestParam("pw") String pw,
                         @RequestParam("email") String email) {

        UserRegisterRequest request = new UserRegisterRequest(id, pw, email);
        service.RegisterUser(request);

        return "redirect:/index";
    }
}
