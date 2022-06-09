package com.nhnacademy.serrayaccountapi.service.impl;

import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.entity.User;
import com.nhnacademy.serrayaccountapi.repository.UserRepository;
import com.nhnacademy.serrayaccountapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository uRepository;

    @Transactional
    @Override
    public void userRegister(UserRegisterResponse response) {

        User user = new User(response.getId(), response.getPw(), response.getEmail(), "가입");

        uRepository.save(user);
    }
}
