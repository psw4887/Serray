package com.nhnacademy.serraytaskapi.data.vo;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MileModifyVO {

    private Integer mileNo;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Length(min=1, max=50, message = "내용은 1~100글자 사이여야합니다.")
    private String content;
}
