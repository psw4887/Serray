package com.nhnacademy.serrayclient.service.impl;

import com.nhnacademy.serrayclient.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final HttpHeaders httpHeaders = buildHeaders();
    private final String gateWayIp;

    private final RestTemplate template;

    @Override
    public boolean isProjectAdmin(Integer projectNo, String id) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Boolean> response =
                template.exchange(gateWayIp + "/projects/" + projectNo + "/auths/admins?id=" + id,
                        HttpMethod.GET,
                        requestEntity,
                        Boolean.class);

        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public boolean isProjectMember(Integer projectNo, String id) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Boolean> response =
                template.exchange(gateWayIp + "/projects/" + projectNo + "/auths/members?id=" + id,
                        HttpMethod.GET,
                        requestEntity,
                        Boolean.class);

        return Boolean.TRUE.equals(response.getBody());
    }

    @Override
    public void registerMember(Integer projectNo, String id) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(gateWayIp + "/projects/" + projectNo + "/members/register?id=" + id,
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteMember(Integer projectNo, String id) {

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        template.exchange(gateWayIp + "/projects/" + projectNo + "/members/delete?id=" + id,
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
