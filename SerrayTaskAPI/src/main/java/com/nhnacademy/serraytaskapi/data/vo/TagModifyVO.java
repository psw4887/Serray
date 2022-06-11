package com.nhnacademy.serraytaskapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagModifyVO {

    private Integer tagNo;

    private String content;
}
