package com.nhnacademy.serrayclient.controller;

import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import com.nhnacademy.serrayclient.git.AuthToken;
import com.nhnacademy.serrayclient.git.CodeGit;
import com.nhnacademy.serrayclient.git.GitProfile;
import com.nhnacademy.serrayclient.service.impl.CustomGitLoginService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GitRestController {

    private final CustomGitLoginService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login/oauth2/code/github")
    public void sendGitLoginInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Model model) throws IOException {
        CodeGit codeGit = new CodeGit(request.getParameter("code"), request.getParameter("state"));
        if (!codeGit.getState().equals(service.getCookie().get("state"))) {
                response.sendRedirect("/auth/login");
        }

        AuthToken authToken = service.getAuthToken(codeGit);
        GitProfile gitProfile = service.getGithubProfile(authToken);
        UserInfoResponse user = service.findUserByEmail(gitProfile, response);
        service.doGitLogin(user);

        response.sendRedirect("/");
    }
}