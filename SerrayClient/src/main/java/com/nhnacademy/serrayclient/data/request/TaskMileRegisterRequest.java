package com.nhnacademy.serrayclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskMileRegisterRequest {

    private Integer taskNo;

    private Integer mileNo;

    private LocalDate start;

    private LocalDate end;
}
