package com.nhnacademy.serrayaccountapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class ForLoginUserVO {

    private String id;

    private String pw;

    private String email;

    private String state;
}
