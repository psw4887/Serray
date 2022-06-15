package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.TaskModifyRequest;
import com.nhnacademy.serrayclient.data.request.TaskRegisterRequest;
import com.nhnacademy.serrayclient.data.response.TaskDataResponse;
import com.nhnacademy.serrayclient.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final String gateWayIp;
    private final HttpHeaders httpHeaders = buildHeaders();
    private final RestTemplate template;
    private final ObjectMapper mapper;
    private String request = "";

    @Override
    public TaskDataResponse getTaskData(Integer taskNo) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<TaskDataResponse> response =
                        template.exchange(gateWayIp + "/projects/tasks/" + taskNo,
                        HttpMethod.GET,
                        requestEntity,
                        TaskDataResponse.class);

        return response.getBody();
    }

    @Override
    public void registerTask(Integer projectNo, String id, String title, String content) {

        TaskRegisterRequest taskRegisterRequest = new TaskRegisterRequest(projectNo, id, title, content);

        try {
            request = mapper.writeValueAsString(taskRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(gateWayIp + "/projects/tasks/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyTask(Integer taskNo, String title, String content) {

        TaskModifyRequest taskModifyRequest = new TaskModifyRequest(taskNo, title, content);

        try {
            request = mapper.writeValueAsString(taskModifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(gateWayIp + "/projects/tasks/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteTask(Integer taskNo) {


        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(gateWayIp + "/projects/tasks/" + taskNo + "/delete",
                HttpMethod.DELETE,
                requestEntity,
                Void.class);
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headers;
    }
}
