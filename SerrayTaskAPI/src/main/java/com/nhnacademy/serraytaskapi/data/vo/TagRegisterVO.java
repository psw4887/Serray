package com.nhnacademy.serraytaskapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRegisterVO {

    private Integer projectNo;

    private String content;

    private String admin;
}
