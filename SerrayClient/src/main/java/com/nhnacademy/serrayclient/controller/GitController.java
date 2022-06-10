package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.git.StateCookie;
import com.nhnacademy.serrayclient.service.impl.CustomGitLoginService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GitController {
    private final CustomGitLoginService service;

    @GetMapping("/oauth2/authorization/github")
    public void readyGitLogin(HttpServletResponse response) {
        try {
            StateCookie stateCookie = service.buildGitRequest();
            response.sendRedirect(stateCookie.getUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
