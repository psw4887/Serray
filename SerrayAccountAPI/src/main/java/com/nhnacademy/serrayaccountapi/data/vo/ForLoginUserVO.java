package com.nhnacademy.serrayaccountapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForLoginUserVO {

    private String id;

    private String pw;

    private String email;

    private String state;
}
