package com.nhnacademy.serrayaccountapi.repository;

import com.nhnacademy.serrayaccountapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
