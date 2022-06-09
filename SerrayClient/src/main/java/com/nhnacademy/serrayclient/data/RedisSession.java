package com.nhnacademy.serrayclient.data;

import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("member")
public class RedisSession {

    @Id
    private String id;
}
