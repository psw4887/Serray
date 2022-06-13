package com.nhnacademy.serraytaskapi.data.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class TagModifyVO {

    @NotNull(message = "태그번호는 NULL일 수 없습니다.")
    private Integer tagNo;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Length(min=1, max=10, message = "내용은 1~10글자 사이여야합니다.")
    private String content;
}
