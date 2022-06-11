package com.nhnacademy.serrayclient.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@RedisHash("member")
public class RedisSession {

    @Id
    private String id;
}
