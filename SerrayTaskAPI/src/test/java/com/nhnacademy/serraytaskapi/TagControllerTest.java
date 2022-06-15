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
import com.nhnacademy.serraytaskapi.controller.TagController;
import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;
import com.nhnacademy.serraytaskapi.service.TagService;
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
@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TagService service;

    @DisplayName("태그 등록 성공")
    @Test
    void registerProjectTag() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TagRegisterVO vo = new TagRegisterVO(1, "content", "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/tags/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("태그 등록 실패")
    @Test
    void registerProjectTagFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TagRegisterVO vo = new TagRegisterVO(1, null, "op");

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(post("/projects/tags/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("태그 수정 성공")
    @Test
    void modifyProjectTag() throws Exception {

        TagModifyVO vo = new TagModifyVO(1, "content");
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/projects/tags/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("태그 수정 실패")
    @Test
    void modifyProjectTagFail() throws Exception {

        TagModifyVO vo = new TagModifyVO(null, null);
        ObjectMapper mapper = new ObjectMapper();

        String requestBody = mapper.writeValueAsString(vo);

        this.mockMvc.perform(put("/projects/tags/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("태그 삭제")
    @Test
    void deleteProjectTag() throws Exception {

        doNothing().when(service).projectTagDelete(anyInt());

        this.mockMvc.perform(delete("/projects/tags/{tagNo}/delete", 10)
                .param("tagNo", "10"))
            .andExpect(status().isOk());

        verify(service, atLeastOnce()).projectTagDelete(10);
    }

    @DisplayName("해당 업무에 태그 등록")
    @Test
    void addTaskTag() throws Exception {

        this.mockMvc.perform(post("/projects/tags/{tagNo}/tasks/{taskNo}/register", 1, 1))
            .andExpect(status().isCreated());
    }
}