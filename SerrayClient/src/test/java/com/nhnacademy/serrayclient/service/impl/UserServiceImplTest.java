package com.nhnacademy.serrayclient.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.data.response.UserIdResponse;
import com.nhnacademy.serrayclient.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService service;

    @MockBean
    RestTemplate template;

    private MockRestServiceServer server;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(template);
    }

    @Test
    void registerUser() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:5050/user/register")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.registerUser(new UserRegisterRequest("user", "123", "p@nav.com"));
    }

    @DisplayName("유저 상태 정보 수정 테스트")
    @Test
    void modifyUserState() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:5050/user/modify/user/가입")))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withStatus(HttpStatus.OK));

        service.modifyUserState("user", "가입");
    }
}