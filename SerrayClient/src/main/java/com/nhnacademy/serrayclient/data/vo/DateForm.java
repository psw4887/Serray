package com.nhnacademy.serrayclient.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
public class DateForm {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "시작날짜는 공백일 수 없습니다.")
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "종료날짜는 공백일 수 없습니다.")
    private LocalDate end;
}
