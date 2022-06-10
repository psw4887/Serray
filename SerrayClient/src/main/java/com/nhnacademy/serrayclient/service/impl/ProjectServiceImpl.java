package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.PostRegisterRequest;
import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import com.nhnacademy.serrayclient.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Override
    public List<ProjectForListResponse> getProjectList(Integer page) {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<ProjectForListResponse>>
            response = restTemplate.exchange("http://localhost:9090/project/view/" + page,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }

    @Override
    public void registerProject(PostRegisterRequest postRegisterRequest) {

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(postRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        restTemplate.exchange("http://localhost:9090/project/register",
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }
}
