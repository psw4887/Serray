package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final PasswordEncoder encoder;

    @GetMapping("/join")
    public String readyJoin() {

        return "Join";
    }

    @PostMapping("/join")
    public String doJoin(@RequestParam("id") String id,
                         @RequestParam("pw") String pw,
                         @RequestParam("email") String email) {

        String pwd = encoder.encode(pw);

        UserRegisterRequest request = new UserRegisterRequest(id, pwd, email);
        service.RegisterUser(request);

        return "redirect:/index";
    }

    @GetMapping("/modify")
    public String changeState(@RequestParam("state") String state,
                              Principal principal) {

        service.modifyUserState(principal.getName(), state);

        return "redirect:/index";
    }
}
