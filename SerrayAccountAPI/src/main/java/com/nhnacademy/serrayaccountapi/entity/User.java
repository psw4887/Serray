package com.nhnacademy.serrayaccountapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_state")
    private String userState;

    public User(String id, String pw, String email, String state) {

        this.userId = id;
        this.userPw = pw;
        this.userEmail = email;
        this.userState = state;
    }
}
