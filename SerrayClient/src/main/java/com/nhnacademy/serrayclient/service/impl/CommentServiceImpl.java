package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.CommentModifyRequest;
import com.nhnacademy.serrayclient.data.request.CommentRegisterRequest;
import com.nhnacademy.serrayclient.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final HttpHeaders httpHeaders = buildHeaders();
    private final String gateWayIp;
    private final RestTemplate template;
    private final ObjectMapper mapper;
    private String request = "";

    @Override
    public String getCommenter(Integer commentNo) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = template.exchange(
                gateWayIp + "/projects/comments/" + commentNo,
                HttpMethod.GET,
                requestEntity,
                String.class);

        return response.getBody();
    }

    @Override
    public void registerComment(Integer taskNo, String admin, String content) {

        CommentRegisterRequest registerRequest = new CommentRegisterRequest(taskNo, admin, content);

        try {
            request = mapper.writeValueAsString(registerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(gateWayIp + "/projects/comments/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyComment(Integer commentNo, String content) {

        CommentModifyRequest modifyRequest = new CommentModifyRequest(commentNo, content);

        try {
            request = mapper.writeValueAsString(modifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(gateWayIp + "/projects/comments/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }
    @Override
    public void deleteComment(Integer commentNo) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(gateWayIp + "/projects/comments/" + commentNo + "/delete",
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
