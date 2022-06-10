package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.TaskModifyRequest;
import com.nhnacademy.serrayclient.data.request.TaskRegisterRequest;
import com.nhnacademy.serrayclient.data.response.TaskModifyDataResponse;
import com.nhnacademy.serrayclient.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final RestTemplate template;
    private final ObjectMapper mapper;

    @Override
    public TaskModifyDataResponse getTaskForModifyData(Integer taskNo) {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TaskModifyDataResponse> response =
                        template.exchange("http://localhost:9090/task?taskNo=" + taskNo,
                        HttpMethod.GET,
                        requestEntity,
                        TaskModifyDataResponse.class);

        return response.getBody();
    }

    @Override
    public void registerTask(Integer projectNo, String id, String title, String content) {

        TaskRegisterRequest taskRegisterRequest = new TaskRegisterRequest(projectNo, id, title, content);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(taskRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange("http://localhost:9090/task/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyTask(Integer taskNo, String title, String content) {

        TaskModifyRequest taskModifyRequest = new TaskModifyRequest(taskNo, title, content);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(taskModifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange("http://localhost:9090/task/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteTask(Integer taskNo) {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange("http://localhost:9090/task/delete/" + taskNo,
                HttpMethod.DELETE,
                requestEntity,
                Void.class);
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }
}
