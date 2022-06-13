package com.nhnacademy.serrayclient.data.vo;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class CommentForm {

    @NotBlank
    @Length(min = 1, max = 50)
    private String content;
}
