package com.nhnacademy.serrayaccountapi.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {

    private String id;

    private String pw;

    private String email;
}
