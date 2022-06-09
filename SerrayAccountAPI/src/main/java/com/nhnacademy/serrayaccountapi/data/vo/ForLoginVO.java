package com.nhnacademy.serrayaccountapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForLoginVO {

    private String id;

    private String pw;

    private String email;
}
