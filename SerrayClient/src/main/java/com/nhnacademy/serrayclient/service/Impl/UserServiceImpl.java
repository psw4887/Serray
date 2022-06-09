package com.nhnacademy.serrayclient.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Override
    public void RegisterUser(UserRegisterRequest userRegisterRequest) {

        HttpHeaders httpHeaders = buildHeaders("");
        String request = "";

        try {
            request = mapper.writeValueAsString(userRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        restTemplate.exchange("http://localhost:5050/user/register",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
    }

    private HttpHeaders buildHeaders(String type) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if(type.equals("list")) {
            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        }

        return httpHeaders;
    }
}
