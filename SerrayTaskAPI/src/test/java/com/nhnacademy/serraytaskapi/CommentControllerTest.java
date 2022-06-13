package com.nhnacademy.serraytaskapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serraytaskapi.controller.CommentController;
import com.nhnacademy.serraytaskapi.data.vo.CommentModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.CommentRegisterVO;
import com.nhnacademy.serraytaskapi.service.CommentService;
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
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CommentService service;

    @DisplayName("/comment/get?commentNo={commentNo} 테스트")
    @Test
    void commenterGet() throws Exception {

        given(service.getCommenter(anyInt())).willReturn("op");

        this.mockMvc.perform(get("/comment/get")
                .param("commentNo", "1"))
            .andExpect(status().isOk())
            .andExpect(content().string("op"));
    }

    @DisplayName("/comment/register 성공 테스트")
    @Test
    void commentRegister() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        CommentRegisterVO vo = new CommentRegisterVO(1, "op", "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/comment/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("/comment/register 실패 테스트")
    @Test
    void commentRegisterFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        CommentRegisterVO vo = new CommentRegisterVO(1, null, "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/comment/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/comment/modify 성공 테스트")
    @Test
    void commentModify() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        CommentModifyVO vo = new CommentModifyVO(1, "content");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/comment/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/comment/modify 실패 테스트")
    @Test
    void commentModifyFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        CommentModifyVO vo = new CommentModifyVO(1, null);

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/comment/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("/comment/delete?commentNo={commentNo} 테스트")
    @Test
    void commentDelete() throws Exception {

        doNothing().when(service).deleteComment(anyInt());

        this.mockMvc.perform(delete("/comment/delete")
                .param("commentNo", "10"))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).deleteComment(10);
    }
}