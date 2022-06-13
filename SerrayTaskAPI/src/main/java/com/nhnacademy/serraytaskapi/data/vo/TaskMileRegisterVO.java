package com.nhnacademy.serraytaskapi.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskMileRegisterVO {

    @NotNull(message = "업무번호는 NULL 일 수 없습니다.")
    private Integer taskNo;

    @NotNull(message = "마일스톤번호는 NULL 일 수 없습니다.")
    private Integer mileNo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "시작일자는 공백일 수 없습니다.")
    private LocalDate start;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "종료일자는 공백일 수 없습니다.")
    private LocalDate end;
}
