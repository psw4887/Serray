package com.nhnacademy.serrayclient.service.impl;

import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HttpHeaders httpHeaders = buildHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserInfoResponse> response = restTemplate.exchange("http://localhost:5050/user/get/" + username,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

        if(Objects.requireNonNull(response.getBody()).getId().equals(username)) {

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_MEMBER");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(authority);

            UserInfoResponse userInfo = response.getBody();

            return new User(Objects.requireNonNull(userInfo).getId(), userInfo.getPw(), authorities);
        }

        throw new UsernameNotFoundException(username + "not found");
    }

    private HttpHeaders buildHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }
}
