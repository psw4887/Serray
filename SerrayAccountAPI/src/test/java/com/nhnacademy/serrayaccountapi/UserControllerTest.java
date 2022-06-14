package com.nhnacademy.serrayaccountapi;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayaccountapi.controller.UserController;
import com.nhnacademy.serrayaccountapi.data.response.OnlyUserIdResponse;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;
import com.nhnacademy.serrayaccountapi.service.UserService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService service;

    @DisplayName("상태가 가입인 유저 리스트 가져오기")
    @Test
    void getStateOKUsers() throws Exception {

        List<OnlyUserIdResponse> list = List.of(new OnlyUserIdResponse("user"));

        given(service.getUserListStateOK()).willReturn(list);

        this.mockMvc.perform(get("/users/state-ok"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value("user"));
    }

    @DisplayName("아이디로 유저 가져오기")
    @Test
    void getUserById() throws Exception {

        ForLoginUserVO vo = new ForLoginUserVO("user", "1", "1@c.com", "가입");

        given(service.findUserById(anyString())).willReturn(vo);

        this.mockMvc.perform(get("/users/{id}", "user"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id",equalTo("user")));
    }

    @DisplayName("이메일로 유저 받아오기")
    @Test
    void getUserByEmail() throws Exception {

        ForLoginUserVO vo = new ForLoginUserVO("user", "1", "1@c.com", "가입");

        given(service.findUserByEmail(anyString())).willReturn(vo);

        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", "psw4887@naver.com"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", equalTo("user")));
    }

    @DisplayName("회원가입 성공 테스트")
    @Test
    void registerUser() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        UserRegisterResponse response = new UserRegisterResponse(
            "xx", "123", "psw0000@naver.com");

        String requestBody = mapper.writeValueAsString(response);

        this.mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isCreated());
    }

    @DisplayName("회원가입 실패 테스트")
    @Test
    void registerUserFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        UserRegisterResponse response = new UserRegisterResponse(
            "xx", null, "psw0000@naver.com");

        String requestBody = mapper.writeValueAsString(response);

        this.mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk());
    }

    @DisplayName("유저 상태 수정 테스트")
    @Test
    void modifyUserState() throws Exception {

        this.mockMvc.perform(put("/users/{user}/modify/states","user")
                .param("state", "휴면"))
            .andExpect(status().isOk());
    }
}