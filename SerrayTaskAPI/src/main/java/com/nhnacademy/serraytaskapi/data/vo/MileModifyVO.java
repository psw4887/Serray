package com.nhnacademy.serraytaskapi.data.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MileModifyVO {

    @NotNull(message = "마일스톤 번호는 NULL 일 수 없습니다.")
    private Integer mileNo;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    @Length(min=1, max=50, message = "내용은 1~100글자 사이여야합니다.")
    private String content;
}
