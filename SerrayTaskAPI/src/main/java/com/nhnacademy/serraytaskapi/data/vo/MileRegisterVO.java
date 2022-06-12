package com.nhnacademy.serraytaskapi.data.vo;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class MileRegisterVO {

    private Integer projectNo;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Length(min=1, max=100, message = "내용은 1~100글자 사이여야합니다.")
    private String content;

    @NotBlank(message = "등록자는 공백일 수 없습니다.")
    @Length(min=1, max=20, message = "등록자는 1~20글자 사이여야합니다.")
    private String admin;
}
