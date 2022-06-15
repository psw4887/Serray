package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.ProjectRegisterRequest;
import com.nhnacademy.serrayclient.data.response.ProjectForDetailResponse;
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

    private final String gateWayIp;
    private final HttpHeaders httpHeaders = buildHeaders();
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Override
    public List<ProjectForListResponse> getProjectList(Integer page) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<ProjectForListResponse>>
            response = restTemplate.exchange(gateWayIp + "/projects/view/" + page,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }

    @Override
    public void registerProject(ProjectRegisterRequest projectRegisterRequest) {

        String request;
        try {
            request = mapper.writeValueAsString(projectRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        restTemplate.exchange(gateWayIp + "/projects/register",
            HttpMethod.POST,
            requestEntity,
            Void.class);
    }

    @Override
    public ProjectForDetailResponse detailProject(Integer projectNo, Integer page) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<ProjectForDetailResponse> response = restTemplate.exchange(
            gateWayIp + "/projects/detail/" + projectNo + "/" + page,
            HttpMethod.GET,
            requestEntity,
            ProjectForDetailResponse.class);

        return response.getBody();
    }

    @Override
    public void projectModifyState(Integer projectNo, String state) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(
                gateWayIp + "/projects/" + projectNo + "/states?state=" + state,
                HttpMethod.PUT,
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
