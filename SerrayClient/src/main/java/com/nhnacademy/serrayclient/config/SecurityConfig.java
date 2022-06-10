package com.nhnacademy.serrayclient.config;

import com.nhnacademy.serrayclient.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/index", "/login", "/join").permitAll()
                .antMatchers("/project/*", "/logout").authenticated()
                .anyRequest().permitAll();
        httpSecurity
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pw")
                .loginPage("/auth/login")
                .loginProcessingUrl("/login");
        httpSecurity
                .logout()
                .logoutUrl("/logout");
        httpSecurity
                .headers()
                .defaultsDisabled()
                .frameOptions()
                .sameOrigin();
        httpSecurity
                .authenticationProvider(authenticationProvider(null));

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
