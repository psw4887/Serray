package com.nhnacademy.serraytaskapi.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.response.ProjectDetailResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.List;
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
@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProjectService service;

    @DisplayName("/project/view/{page} 테스트")
    @Test
    void getPageableProjects() throws Exception {

        List<PageableProjectResponse> list = List.of(new PageableProjectResponse(1, "op", "제목", "종료"));

        given(service.getPageableProjectList(anyInt())).willReturn(list);

        this.mockMvc.perform(get("/project/view/{page}", 0))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].title").value("제목"));
    }

    @DisplayName("/project/register 성공 테스트")
    @Test
    void projectRegister() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ProjectRegisterVO vo = new ProjectRegisterVO("op", "제목", "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/project/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("/project/register 실패 테스트")
    @Test
    void projectRegisterFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ProjectRegisterVO vo = new ProjectRegisterVO("op", null, "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/project/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/project/detail/{projectNo}/{page} 테스트")
    @Test
    void getProjectDetail() throws Exception {

        ProjectDetailResponse detailResponse = new ProjectDetailResponse(
            "op", "제목", "content", "종료",
            null, null, null, null);

        given(service.getDetailProject(anyInt(), anyInt())).willReturn(detailResponse);

        this.mockMvc.perform(get("/project/detail/{projectNo}/{page}", 1, 2))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title", equalTo("제목")));
    }

    @DisplayName("/project/state/{projectNo} 테스트")
    @Test
    void modifyProjectState() throws Exception {

        this.mockMvc.perform(put("/project/state/{projectNo}","1")
                .param("state", "종료"))
            .andExpect(status().isOk());
    }
}