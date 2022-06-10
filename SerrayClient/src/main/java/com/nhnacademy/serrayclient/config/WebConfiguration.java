package com.nhnacademy.serrayclient.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.Duration;

@EnableSpringDataWebSupport
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/login").setViewName("login");
        registry.addViewController("/auth/logout").setViewName("logout");
        registry.addViewController("/user/join").setViewName("join");
        registry.addViewController("/project/register").setViewName("project/projectList");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setReadTimeout(Duration.ofSeconds(5L))
                .setConnectTimeout(Duration.ofSeconds(3L))
                .build();
    }


}