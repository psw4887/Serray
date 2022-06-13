package com.nhnacademy.serrayclient.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.TagService;
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
class TagServiceImplTest {

    @Autowired
    TagService service;

    @MockBean
    RestTemplate template;

    private MockRestServiceServer server;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(template);
    }

    @DisplayName("태그 등록 테스트")
    @Test
    void registerTag() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/tag/register")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.registerTag(1, "content", "op");
    }

    @DisplayName("태그 수정 테스트")
    @Test
    void modifyTag() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/project/tag/modify")))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withStatus(HttpStatus.OK));

        service.modifyTag(1, "활성");
    }

    @DisplayName("태그 삭제 테스트")
    @Test
    void deleteTag() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/tag/delete?tagNo=1")))
            .andExpect(method(HttpMethod.DELETE))
            .andRespond(withStatus(HttpStatus.OK));

        service.deleteTag(1);
    }

    @DisplayName("업무 태그 등록 테스트")
    @Test
    void addTaskTag() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/tag/task/register?taskNo=1&tagNo=1")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.addTaskTag(1, 1);
    }
}