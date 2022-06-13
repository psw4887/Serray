package com.nhnacademy.serrayclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static reactor.core.publisher.Mono.when;

import com.nhnacademy.serrayclient.service.TagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TagService service;

    @DisplayName("태그 수정 준비")
    @Test
    void readyTagModify() throws Exception {

        MvcResult mvcResult =  mockMvc.perform(get("/tag/modify")
            .param("projectNo", "1")
            .param("tagNo", "1")
            .param("content", "Hello"))
            .andExpect(status().isOk())
            .andExpect(view().name("tag/tagModify"))
            .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("projectNo")).isEqualTo(1);
        assertThat(mvcResult.getModelAndView().getModel().get("tagNo")).isEqualTo(1);
        assertThat(mvcResult.getModelAndView().getModel().get("content")).isEqualTo("Hello");
    }

    @DisplayName("태그 수정")
    @Test
    void tagModify() throws Exception {

        doNothing().when(service).modifyTag(anyInt(), anyString());

        mockMvc.perform(post("/tag/modify")
                .param("projectNo", "1")
                .param("tagNo", "1")
                .param("content", "Hello"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/project/detail/1?page=0"));

        verify(service, atLeastOnce()).modifyTag(anyInt(), anyString());
    }

    @DisplayName("태그 삭제")
    @Test
    void tagDelete() throws Exception {

        doNothing().when(service).deleteTag(anyInt());

        mockMvc.perform(get("/tag/delete")
                .param("projectNo", "1")
                .param("tagNo", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/project/detail/1?page=0"));

        verify(service, atLeastOnce()).deleteTag(anyInt());
    }

    @DisplayName("업무 태그 등록")
    @Test
    void taskTagRegister() throws Exception {

        doNothing().when(service).addTaskTag(anyInt(), anyInt());

        mockMvc.perform(post("/tag/task/register")
                .param("projectNo", "1")
                .param("taskNo", "1")
                .param("tagNo", "1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(result -> assertThat(result.getResponse().getRedirectedUrl())
                .isEqualTo("/task/detail?taskNo=1&projectNo=1"));

        verify(service, atLeastOnce()).addTaskTag(anyInt(), anyInt());
    }
}