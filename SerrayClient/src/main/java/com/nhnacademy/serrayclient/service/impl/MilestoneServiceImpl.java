package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.MileModifyRequest;
import com.nhnacademy.serrayclient.data.request.MileRegisterRequest;
import com.nhnacademy.serrayclient.data.request.TaskMileRegisterRequest;
import com.nhnacademy.serrayclient.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final String gateWayIp;
    private String request = "";
    private final HttpHeaders httpHeaders = buildHeaders();
    private final RestTemplate template;
    private final ObjectMapper mapper;
    @Override
    public void registerMilestone(Integer projectNo, String content, String admin) {

        MileRegisterRequest registerRequest = new MileRegisterRequest(projectNo, content, admin);

        try {
            request = mapper.writeValueAsString(registerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                gateWayIp + "/projects/miles/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyMilestone(Integer mileNo, String content) {

        MileModifyRequest modifyRequest = new MileModifyRequest(mileNo, content);

        try {
            request = mapper.writeValueAsString(modifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                gateWayIp + "/projects/miles/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteMilestone(Integer mileNo) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(
                gateWayIp + "/projects/miles/" + mileNo + "/delete",
                HttpMethod.DELETE,
                requestEntity,
                Void.class);
    }

    @Override
    public void addTaskMile(Integer mileNo, Integer taskNo, LocalDate start, LocalDate end) {

        TaskMileRegisterRequest registerRequest = new TaskMileRegisterRequest(
                taskNo, mileNo, start, end
        );

        try {
            request = mapper.writeValueAsString(registerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                gateWayIp + "/projects/miles/tasks/register",
                HttpMethod.POST,
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
