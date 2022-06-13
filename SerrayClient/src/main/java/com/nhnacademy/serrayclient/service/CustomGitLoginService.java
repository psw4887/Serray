package com.nhnacademy.serrayclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import com.nhnacademy.serrayclient.data.git.AuthToken;
import com.nhnacademy.serrayclient.data.git.CodeGit;
import com.nhnacademy.serrayclient.data.git.GitProfile;
import com.nhnacademy.serrayclient.data.git.StateCookie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service("customGitLoginService")
@RequiredArgsConstructor
public class CustomGitLoginService {

    private final UserService uService;
    private final ConcurrentHashMap<String, String> cookie = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> getCookie() {
        return cookie;
    }

    public void doGitLogin(UserInfoResponse userInfo) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_MEMBER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        UserDetails userDetails = new User(userInfo.getId(), userInfo.getPw(), authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "USER_PASSWORD", authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    public UserInfoResponse findUserByEmail(GitProfile gitProfile, HttpServletResponse response) throws IOException {
        if (Objects.isNull(uService.findByUserEmail(gitProfile.getEmail()))) {
            response.sendRedirect("auth/login");
        }

        return uService.findByUserEmail(gitProfile.getEmail());
    }

    public StateCookie buildGitRequest() {
        String state = UUID.randomUUID().toString();

        UriComponents url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize")
                .queryParam("client_id", "fcaf07655762ce4a267b")
                .queryParam("redirect_uri", "http://localhost:8080/login/oauth2/code/github")
                .queryParam("scope", "user")
                .queryParam("state", state)
                .build();
        cookie.put("state", state);

        return new StateCookie(state, url.toString());
    }

    public AuthToken getAuthToken(CodeGit codeGit) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String, String>> codeRequestHttpEntity = getCodeRequestHttpEntity(codeGit.getCode());
        RestTemplate tokenRequestTemplate = new RestTemplate();

        ResponseEntity<String> response = tokenRequestTemplate.exchange(
                "https://github.com/login/oauth/access_token",
                HttpMethod.POST,
                codeRequestHttpEntity,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), AuthToken.class);
    }

    public GitProfile getGithubProfile(AuthToken authToken) throws JsonProcessingException {
        RestTemplate requestTemplate = new RestTemplate();
        ResponseEntity<String> profileResponse = requestTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                getProfileRequestEntity(authToken),
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(profileResponse.getBody(), GitProfile.class);
    }

    private HttpEntity<MultiValueMap<String, String>> getCodeRequestHttpEntity(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "fcaf07655762ce4a267b");
        params.add("client_secret", "22e83265d9668b2f67f4f0570f57ca2877dc9509");
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> getProfileRequestEntity(AuthToken authToken) {

        HttpHeaders infoRequestHeaders = new HttpHeaders();
        infoRequestHeaders.add("Authorization", "token " + authToken.getAccessToken());
        return new HttpEntity<>(infoRequestHeaders);
    }
}
