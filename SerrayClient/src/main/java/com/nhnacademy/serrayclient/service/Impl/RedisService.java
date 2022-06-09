package com.nhnacademy.serrayclient.service.Impl;

import com.nhnacademy.serrayclient.data.RedisSession;
import com.nhnacademy.serrayclient.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisRepository repository;

    public void addRedis() {

        RedisSession redis = new RedisSession("op");
        repository.save(redis);
    }
}
