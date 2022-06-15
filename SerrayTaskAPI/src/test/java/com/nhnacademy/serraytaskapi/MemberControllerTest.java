package com.nhnacademy.serraytaskapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.serraytaskapi.controller.MemberController;
import com.nhnacademy.serraytaskapi.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MemberService service;

    @DisplayName("해당 프로젝트의 관리자 멤버인지 확인")
    @Test
    void isProjectAdmin() throws Exception {

        given(service.isAdmin(anyInt(), anyString())).willReturn(true);

        this.mockMvc.perform(get("/projects/{projectNo}/auths/admins", 1)
                .param("id", "op"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").value("true"));
    }

    @DisplayName("해당 프로젝트의 구성원 멤버인지 확인")
    @Test
    void isProjectMember() throws Exception {

        given(service.isMember(anyInt(), anyString())).willReturn(true);

        this.mockMvc.perform(get("/projects/{projectNo}/auths/members", 1)
                .param("id", "op"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").value("true"));
    }

    @DisplayName("해당 프로젝트에 멤버 추가")
    @Test
    void memberRegister() throws Exception {

        this.mockMvc.perform(post("/projects/{projectNo}/members/register", 1)
                .param("id", "op"))
            .andExpect(status().isCreated());
    }

    @DisplayName("해당 프로젝트에 멤버 삭제")
    @Test
    void memberDelete() throws Exception {

        doNothing().when(service).deleteMember(anyInt(), anyString());

        this.mockMvc.perform(delete("/projects/{projectNo}/members/delete", 1)
                .param("id", "op"))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).deleteMember(1, "op");
    }
}