package com.nhnacademy.serrayclient.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.TaskService;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    TaskService service;

    @MockBean
    RestTemplate template;

    private MockRestServiceServer server;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(template);
    }

    @DisplayName("업무 등록 테스트")
    @Test
    void registerTask() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/task/register")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.registerTask(1, "content", "op", "op");
    }

    @DisplayName("업무 수정 테스트")
    @Test
    void modifyTask() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/task/modify")))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withStatus(HttpStatus.OK));

        service.modifyTask(1, "활성", "내용");
    }

    @DisplayName("업무 삭제 테스트")
    @Test
    void deleteTask() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/task/delete?taskNo=1")))
            .andExpect(method(HttpMethod.DELETE))
            .andRespond(withStatus(HttpStatus.OK));

        service.deleteTask(1);
    }
}