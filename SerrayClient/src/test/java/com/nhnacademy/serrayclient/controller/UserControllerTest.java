package com.nhnacademy.serrayclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.serrayclient.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService service;
    @MockBean
    PasswordEncoder encoder;

    @DisplayName("/join 테스트")
    @Test
    void readyJoin() throws Exception {

        mockMvc.perform(get("/user/join"))
            .andExpect(status().isOk())
            .andExpect(view().name("join"));
    }

    @DisplayName("/user/join 테스트")
    @Test
    void doJoin() throws Exception {

        mockMvc.perform(post("/user/join")
                .param("id", "user")
                .param("pw", "123")
                .param("email", "psw4887@naver.com"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/index"));
    }

    @DisplayName("/user/modify/{now} 테스트")
    @Test
    void changeState() throws Exception {

        mockMvc.perform(get("/user/modify/{now}", "비회원")
                .param("state", "탈퇴"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/index"));
    }
}