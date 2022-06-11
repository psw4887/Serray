package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.request.TagModifyRequest;
import com.nhnacademy.serrayclient.data.request.TagRegisterRequest;
import com.nhnacademy.serrayclient.service.TagService;
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
public class TagServiceImpl implements TagService {

    private final RestTemplate template;
    private final ObjectMapper mapper;

    @Override
    public void registerTag(Integer projectNo, String content, String admin) {

        TagRegisterRequest registerRequest = new TagRegisterRequest(projectNo, content, admin);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(registerRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                "http://localhost:9090/tag/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyTag(Integer tagNo, String content) {

        TagModifyRequest modifyRequest = new TagModifyRequest(tagNo, content);

        HttpHeaders httpHeaders = buildHeaders();
        String request = "";

        try {
            request = mapper.writeValueAsString(modifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        template.exchange(
                "http://localhost:9090/tag/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);


    }

    @Override
    public void deleteTag(Integer tagNo) {

        HttpHeaders httpHeaders = buildHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(
                "http://localhost:9090/tag/delete?tagNo=" + tagNo,
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
