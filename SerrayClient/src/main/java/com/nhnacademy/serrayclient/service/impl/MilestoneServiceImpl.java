package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.MileModifyRequest;
import com.nhnacademy.serrayclient.data.request.MileRegisterRequest;
import com.nhnacademy.serrayclient.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final RestTemplate template;
    private final ObjectMapper mapper;
    @Override
    public void registerMilestone(Integer projectNo, String content, String admin) {

        MileRegisterRequest registerRequest = new MileRegisterRequest(projectNo, content, admin);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(registerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                "http://localhost:9090/mile/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyMilestone(Integer mileNo, String content) {

        MileModifyRequest modifyRequest = new MileModifyRequest(mileNo, content);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(modifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                "http://localhost:9090/mile/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteMilestone(Integer mileNo) {

        HttpHeaders httpHeaders = buildHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(
                "http://localhost:9090/mile/delete?mileNo=" + mileNo,
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
