package com.nhnacademy.serrayclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.serrayclient.data.response.CommentDataResponse;
import com.nhnacademy.serrayclient.data.response.TaskDataResponse;
import com.nhnacademy.serrayclient.service.TaskService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WithMockUser
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService service;

    @DisplayName("테스크 정보 불러오기")
    @Test
    void taskDetail() throws Exception {

        TaskDataResponse taskDataResponse = new TaskDataResponse(1, "op", "제목", "내용",
            List.of(), List.of(), List.of(), List.of(), null);

        when(service.getTaskData(anyInt())).thenReturn(taskDataResponse);

        MvcResult result = mockMvc.perform(get("/task/detail")
            .param("taskNo", "1")
            .param("projectNo", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("task/taskDetail"))
            .andReturn();

        assertThat(result.getModelAndView().getModel().get("taskNo")).isEqualTo(1);
        assertThat(result.getModelAndView().getModel().get("projectNo")).isEqualTo(1);
        assertThat(result.getModelAndView().getModel().get("task")).isEqualTo(taskDataResponse);
        assertThat(result.getModelAndView().getModel().get("taskMiles")).isEqualTo(0);
    }

    @Test
    void readyTaskRegister() throws Exception {

        MvcResult result = mockMvc.perform(get("/task/register")
            .param("projectNo", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("task/taskRegisterForm"))
            .andReturn();

        assertThat(result.getModelAndView().getModel().get("projectNo")).isEqualTo(1);
    }

    @Test
    void taskModify() throws Exception {

        doNothing().when(service).modifyTask(anyInt(), anyString(), anyString());

        mockMvc.perform(post("/task/modify")
            .param("projectNo", "1")
            .param("taskNo", "1")
            .param("title", "제목")
            .param("content", "내용"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/project/detail/1?page=0"));

        verify(service, atLeastOnce()).modifyTask(anyInt(), anyString(), anyString());
    }

    @DisplayName("Valid Test")
    @Test
    void taskModifyValidError() throws Exception {

        doNothing().when(service).modifyTask(anyInt(), anyString(), anyString());

        mockMvc.perform(post("/task/modify")
                .param("projectNo", "1")
                .param("taskNo", "1")
                .param("title", "제목")
                .param("content", "999999999999999999999999999999999999999" +
                    "99999999999999999999999999999999999999999999999999999999999999999" +
                    "99999999999999999999999999999999999999999999999999999999999999999" +
                    "9999999999999999999999999999999999999999999999999999999999999999999" +
                    "9999999999999999999999999999999999999999999999999999999999999999" +
                    "999999999999999999999999999999999999999999999999999999999999999999" +
                    "0000000000000000000000000000000000000000000000000000000000000000000"))
            .andExpect(status().isOk());

        verify(service, times(0)).modifyTask(anyInt(), anyString(), anyString());
    }
}