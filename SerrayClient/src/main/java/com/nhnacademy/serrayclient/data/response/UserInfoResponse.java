package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponse {

    private String id;

    private String pw;

    private String email;
}
