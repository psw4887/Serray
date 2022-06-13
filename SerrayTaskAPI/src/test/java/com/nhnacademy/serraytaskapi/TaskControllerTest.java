package com.nhnacademy.serraytaskapi;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serraytaskapi.controller.TaskController;
import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.service.TaskService;
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
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @DisplayName("/task 테스트")
    @Test
    void getDataByTask() throws Exception {

        TaskDataResponse list = new TaskDataResponse(
            1, "op", "제목", "content",
            null, null, null, null, null
        );

        given(service.getTaskData(anyInt())).willReturn(list);

        this.mockMvc.perform(get("/task")
                .param("taskNo", "1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("제목"));
    }

    @DisplayName("/task/register 성공 테스트")
    @Test
    void taskRegister() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TaskRegisterVO vo = new TaskRegisterVO(1, "op", "제목", "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/task/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("/task/register 실패 테스트")
    @Test
    void taskRegisterFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TaskRegisterVO vo = new TaskRegisterVO(1, "op", null, "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/task/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/task/modify 성공 테스트")
    @Test
    void taskModify() throws Exception {

        TaskModifyVo vo = new TaskModifyVo(1000, "제목", "content");
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/task/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/task/modify 실패 테스트")
    @Test
    void taskModifyFail() throws Exception {

        TaskModifyVo vo = new TaskModifyVo(999999, null, null);
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/task/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/task/delete 테스트")
    @Test
    void taskDelete() throws Exception {

        doNothing().when(service).deleteTask(anyInt());

        this.mockMvc.perform(delete("/task/delete/{taskNo}", 10))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).deleteTask(10);
    }
}