package com.nhnacademy.serrayclient.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

        HttpHeaders httpHeaders = buildHeaders();
    }

    @Override
    public void modifyMilestone(Integer mileNo, String content) {

        HttpHeaders httpHeaders = buildHeaders();
    }

    @Override
    public void deleteMilestone(Integer mileNo) {

        HttpHeaders httpHeaders = buildHeaders();
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }
}
