package com.nhnacademy.serrayaccountapi.repository;

import com.nhnacademy.serrayaccountapi.data.dto.ForLoginDTO;
import com.nhnacademy.serrayaccountapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userId = ?1")
    ForLoginDTO getUserForLogin(String id);

    User getUserByUserId(String id);
}
