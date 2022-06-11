package com.nhnacademy.serrayclient.repository;

import com.nhnacademy.serrayclient.data.RedisSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRepository extends CrudRepository<RedisSession , String> {

}
