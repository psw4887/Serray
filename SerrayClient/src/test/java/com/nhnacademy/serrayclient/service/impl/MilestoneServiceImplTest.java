package com.nhnacademy.serrayclient.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.MilestoneService;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
class MilestoneServiceImplTest {

    @Autowired
    MilestoneService service;

    @MockBean
    RestTemplate template;

    private MockRestServiceServer server;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        server = MockRestServiceServer.createServer(template);
    }

    @DisplayName("마일스톤 추가 테스트")
    @Test
    void registerMilestone() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/mile/register")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.registerMilestone(1, "user", "op");
    }

    @DisplayName("마일스톤 수정 테스트")
    @Test
    void modifyMilestone() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/mile/modify")))
            .andExpect(method(HttpMethod.PUT))
            .andRespond(withStatus(HttpStatus.OK));

        service.modifyMilestone(1, "op");
    }

    @DisplayName("마일스톤 삭제 테스트")
    @Test
    void deleteMilestone() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/mile/delete?mileNo=1")))
            .andExpect(method(HttpMethod.DELETE))
            .andRespond(withStatus(HttpStatus.OK));

        service.deleteMilestone(1);
    }

    @DisplayName("업무 마일스톤 추가 테스트")
    @Test
    void addTaskMile() throws URISyntaxException {

        server.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:9090/mile/task/register")))
            .andExpect(method(HttpMethod.POST))
            .andRespond(withStatus(HttpStatus.CREATED));

        service.addTaskMile(1, 1, LocalDate.now(), LocalDate.now());
    }
}