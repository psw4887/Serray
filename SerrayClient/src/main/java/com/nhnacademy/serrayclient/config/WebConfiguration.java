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
        registry.addViewController("/error/403").setViewName("error403");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setReadTimeout(Duration.ofSeconds(10L))
                .setConnectTimeout(Duration.ofSeconds(5L))
                .build();
    }

    @Bean
    public String gateWayIp() {

        return "http://localhost:7070";
    }
}