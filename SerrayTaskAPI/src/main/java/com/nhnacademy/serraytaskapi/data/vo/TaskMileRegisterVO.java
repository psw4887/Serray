package com.nhnacademy.serraytaskapi.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TaskMileRegisterVO {

    private Integer taskNo;

    private Integer mileNo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "시작일자는 공백일 수 없습니다.")
    private LocalDate start;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "종료일자는 공백일 수 없습니다.")
    private LocalDate end;
}
