package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDataResponse {

    private Integer mileNo;

    private String content;

    private LocalDate start;

    private LocalDate end;
}
