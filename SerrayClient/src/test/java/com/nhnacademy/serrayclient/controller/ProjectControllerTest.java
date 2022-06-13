package com.nhnacademy.serrayclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import com.nhnacademy.serrayclient.service.MemberService;
import com.nhnacademy.serrayclient.service.ProjectService;
import com.nhnacademy.serrayclient.service.UserService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProjectService projectService;
    @MockBean
    UserService userService;
    @MockBean
    MemberService memberService;

    @DisplayName("GET /project/view?page={page} 테스트")
    @Test
    void projectListView() throws Exception {

        when(projectService.getProjectList(anyInt())).thenReturn(
            List.of(new ProjectForListResponse(1, "op", "제목", "content")));

        MvcResult mvcResult = mockMvc.perform(get("/project/view")
            .param("page", "0"))
            .andExpect(status().isOk())
            .andExpect(view().name("project/projectList"))
            .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("isEnd")).isEqualTo(1);
        assertThat(mvcResult.getModelAndView().getModel().get("lists"))
            .isEqualTo(List.of(new ProjectForListResponse(1, "op", "제목", "content")));
        assertThat(mvcResult.getModelAndView().getModel().get("page")).isEqualTo(0);
    }

    @DisplayName("GET /project/register 테스트")
    @Test
    void readyProjectRegister() throws Exception {

        this.mockMvc.perform(get("/project/register"))
            .andExpect(status().isOk())
            .andExpect(view().name("project/projectRegisterForm"));
    }

    @DisplayName("POST /project/register 실패 테스트")
    @Test
    void projectRegisterFail() throws Exception{

        this.mockMvc.perform(post("/project/register")
                .param("title", "제목")
                .param("content", "내용"))
            .andExpect(status().isOk());
    }
}