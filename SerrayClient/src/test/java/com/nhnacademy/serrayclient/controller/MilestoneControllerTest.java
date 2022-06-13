package com.nhnacademy.serrayclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.serrayclient.service.MilestoneService;
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
@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MilestoneService service;

    @DisplayName("마일스톤 수정 준비")
    @Test
    void readyMileModify() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/mile/modify")
            .param("projectNo", "1")
            .param("mileNo", "1")
            .param("content", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("milestone/milestoneModify"))
            .andReturn();

        assertThat(result.getModelAndView().getModel().get("projectNo")).isEqualTo(1);
        assertThat(result.getModelAndView().getModel().get("mileNo")).isEqualTo(1);
        assertThat(result.getModelAndView().getModel().get("content")).isEqualTo("1");
    }

    @DisplayName("마일스톤 수정")
    @Test
    void mileModify() throws Exception {

        doNothing().when(service).modifyMilestone(anyInt(), anyString());

        mockMvc.perform(post("/mile/modify")
            .param("projectNo", "1")
            .param("mileNo", "1")
            .param("content", "hello"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/project/detail/1?page=0"));

        verify(service, atLeastOnce()).modifyMilestone(anyInt(), anyString());
    }

    @DisplayName("마일스톤 삭제")
    @Test
    void mileDelete() throws Exception {

        doNothing().when(service).deleteMilestone(anyInt());

        mockMvc.perform(get("/mile/delete")
            .param("projectNo", "1")
            .param("mileNo", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/project/detail/1?page=0"));

        verify(service, atLeastOnce()).deleteMilestone(anyInt());
    }

    @DisplayName("업무 마일스톤 등록")
    @Test
    void taskMileRegister() throws Exception {

        doNothing().when(service).addTaskMile(anyInt(), anyInt(), any(), any());

        mockMvc.perform(post("/mile/task/register")
            .param("projectNo", "1")
            .param("taskNo", "1")
            .param("mileNo", "1")
            .param("start", "2022-02-01")
            .param("end", "2022-02-09"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/task/detail?taskNo=1&projectNo=1"));

        verify(service, atLeastOnce()).addTaskMile(anyInt(), anyInt(), any(), any());
    }
}