package com.nhnacademy.serrayclient.service.impl;

import com.nhnacademy.serrayclient.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RestTemplate template;

    @Override
    public boolean isProjectAdmin(Integer projectNo, String id) {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Boolean> response =
                template.exchange("http://localhost:9090/members/admin?projectNo=" + projectNo + "&id=" + id,
                        HttpMethod.GET,
                        requestEntity,
                        Boolean.class);

        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public boolean isProjectMember(Integer projectNo, String id) {
        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Boolean> response =
                template.exchange("http://localhost:9090/members/member?projectNo=" + projectNo + "&id=" + id,
                        HttpMethod.GET,
                        requestEntity,
                        Boolean.class);

        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public void registerMember(Integer projectNo, String id) {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange("http://localhost:9090/members/register?projectNo=" + projectNo + "&id=" + id,
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteMember(Integer projectNo, String id) {
        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange("http://localhost:9090/members/delete?projectNo=" + projectNo + "&id=" + id,
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
