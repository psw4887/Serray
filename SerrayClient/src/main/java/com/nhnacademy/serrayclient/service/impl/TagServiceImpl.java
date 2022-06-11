package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

        HttpHeaders httpHeaders = buildHeaders();
    }

    @Override
    public void modifyTag(Integer tagNo, String content) {

        HttpHeaders httpHeaders = buildHeaders();
    }

    @Override
    public void deleteTag(Integer tagNo) {

        HttpHeaders httpHeaders = buildHeaders();
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }
}
