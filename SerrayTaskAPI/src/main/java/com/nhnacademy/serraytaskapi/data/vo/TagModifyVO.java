package com.nhnacademy.serraytaskapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class TagModifyVO {

    private Integer tagNo;

    @Length(min=1, max=10, message = "내용은 1~10글자 사이여야합니다.")
    private String content;
}
