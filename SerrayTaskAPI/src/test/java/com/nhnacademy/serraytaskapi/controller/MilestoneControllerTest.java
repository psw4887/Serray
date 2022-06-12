package com.nhnacademy.serraytaskapi.controller;

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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MilestoneService service;

    @DisplayName("/mile/register 성공 테스트")
    @Test
    void registerProjectMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileRegisterVO vo = new MileRegisterVO(1, "content", "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/mile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("/mile/register 실패 테스트")
    @Test
    void registerProjectMileFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileRegisterVO vo = new MileRegisterVO(null, null, "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/mile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/mile/modify 성공 테스트")
    @Test
    void modifyProjectMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileModifyVO vo = new MileModifyVO(1 , "content");
        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/mile/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/mile/modify 실패 테스트")
    @Test
    void modifyProjectMileFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        MileModifyVO vo = new MileModifyVO(null , null);
        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/mile/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/mile/delete?mileNo={mileNo} 테스트")
    @Test
    void deleteProjectMile() throws Exception {

        doNothing().when(service).projectMileDelete(anyInt());

        this.mockMvc.perform(delete("/mile/delete")
                .param("mileNo", "10"))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).projectMileDelete(10);
    }

    @DisplayName("/mile/task/register 성공 테스트")
    @Test
    void addTaskMile() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TaskMileRegisterVO vo = new TaskMileRegisterVO();

        vo.setMileNo(9);
        vo.setTaskNo(10);
        vo.setStart(LocalDate.now());
        vo.setEnd(LocalDate.now());

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/mile/task/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("/mile/task/register 실패 테스트")
    @Test
    void addTaskMileFail() throws Exception {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        OBJECT_MAPPER.registerModule(javaTimeModule);
        JacksonTester.initFields(this, OBJECT_MAPPER);

        ObjectMapper mapper = new ObjectMapper();
        TaskMileRegisterVO vo = new TaskMileRegisterVO();

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/mile/task/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }
}