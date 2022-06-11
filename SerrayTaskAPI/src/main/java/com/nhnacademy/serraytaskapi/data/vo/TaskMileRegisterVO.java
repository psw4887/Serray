package com.nhnacademy.serraytaskapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskMileRegisterVO {

    private Integer taskNo;

    private Integer mileNo;

    private LocalDate start;

    private LocalDate end;
}
