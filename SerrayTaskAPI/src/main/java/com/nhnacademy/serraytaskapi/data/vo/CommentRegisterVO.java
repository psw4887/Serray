package com.nhnacademy.serraytaskapi.data.vo;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class CommentRegisterVO {

    private Integer taskNo;

    @NotBlank(message = "작성자는 공백일 수 없습니다.")
    @Length(min=1, max=10, message = "작성자는 1~20글자 사이여야합니다.")
    private String admin;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Length(min=1, max=10, message = "내용은 1~50글자 사이여야합니다.")
    private String content;
}
