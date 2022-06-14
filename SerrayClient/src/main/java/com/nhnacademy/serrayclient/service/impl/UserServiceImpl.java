package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.data.response.UserIdResponse;
import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import com.nhnacademy.serrayclient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final String gateWayIp;
    private final HttpHeaders httpHeaders = buildHeaders();
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Override
    public List<UserIdResponse> getUsersForStateOK() {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<UserIdResponse>> response = restTemplate.exchange(gateWayIp + "/users/state-ok",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    @Override
    public UserInfoResponse getUser(String id) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserInfoResponse> response = restTemplate.exchange(gateWayIp + "/users/" + id,
                HttpMethod.GET,
                requestEntity,
                UserInfoResponse.class);

        return response.getBody();
    }

    @Override
    public UserInfoResponse findByUserEmail(String email) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserInfoResponse> response = restTemplate.exchange(gateWayIp + "/users?email=" + email,
            HttpMethod.GET,
            requestEntity,
            UserInfoResponse.class);

        return response.getBody();
    }

    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {

        String request;
        try {
            request = mapper.writeValueAsString(userRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        restTemplate.exchange(gateWayIp + "/users/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyUserState(String user, String state) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        restTemplate.exchange(gateWayIp + "/users/" + user + "/modify/states?state=" + state,
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
