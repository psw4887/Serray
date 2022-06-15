package com.nhnacademy.serraytaskapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.nhnacademy.serraytaskapi.controller.MilestoneController;
import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TaskMileRegisterVO;
import com.nhnacademy.serraytaskapi.service.MilestoneService;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MilestoneService service;

    @DisplayName("마일스톤 등록 성공")
    @Test
    void registerProjectMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileRegisterVO vo = new MileRegisterVO(1, "content", "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/miles/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("마일스톤 등록 실패")
    @Test
    void registerProjectMileFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileRegisterVO vo = new MileRegisterVO(null, null, "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/miles/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("마일스톤 수정 성공")
    @Test
    void modifyProjectMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileModifyVO vo = new MileModifyVO(1 , "content");
        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/projects/miles/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("마일스톤 수정 실패")
    @Test
    void modifyProjectMileFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileModifyVO vo = new MileModifyVO(null , null);
        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/projects/miles/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("마일스톤 삭제")
    @Test
    void deleteProjectMile() throws Exception {

        doNothing().when(service).projectMileDelete(anyInt());

        this.mockMvc.perform(delete("/projects/miles/10/delete"))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).projectMileDelete(10);
    }

    @DisplayName("업무 마일스톤 등록 성공")
    @Test
    void addTaskMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TaskMileRegisterVO vo = new TaskMileRegisterVO(9, 10, LocalDate.now(), LocalDate.now());

        String requestBody = mapper.registerModule(new JavaTimeModule()).writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/miles/tasks/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("업무 마일스톤 등록 실패")
    @Test
    void addTaskMileFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TaskMileRegisterVO vo = new TaskMileRegisterVO(null, null, null, null);

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/miles/tasks/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }
}