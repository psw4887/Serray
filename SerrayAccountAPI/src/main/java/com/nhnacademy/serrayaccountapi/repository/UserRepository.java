package com.nhnacademy.serrayaccountapi.repository;

import com.nhnacademy.serrayaccountapi.data.dto.ForLoginDTO;
import com.nhnacademy.serrayaccountapi.data.dto.OnlyUserIdDTO;
import com.nhnacademy.serrayaccountapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u.userId as userId from User u where u.userState = '가입'")
    List<OnlyUserIdDTO> getUserIdsByStateIsOK();

    @Query("select u from User u where u.userId = ?1")
    Optional<ForLoginDTO> getUserForLogin(String id);

    User getUserByUserId(String id);

    @Query("select u from User u where u.userEmail = ?1")
    ForLoginDTO getUserForGitLogin(String email);
}
