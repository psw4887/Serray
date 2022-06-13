package com.nhnacademy.serrayclient.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.MemberService;
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
class MemberServiceImplTest {

    @Autowired
    MemberService service;

    @MockBean
    RestTemplate template;

    private MockRestServiceServer server;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(template);
    }

    @DisplayName("멤버 추가 테스트")
    @Test
    void registerMember() throws URISyntaxException {
        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/members/register?projectNo=1&id=user")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.registerMember(1, "user");
    }

    @DisplayName("멤버 삭제 테스트")
    @Test
    void deleteMember() throws URISyntaxException {
        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/members/delete?projectNo=1&id=user")))
            .andExpect(method(HttpMethod.DELETE))
            .andRespond(withStatus(HttpStatus.OK));

        service.deleteMember(1, "user");
    }
}