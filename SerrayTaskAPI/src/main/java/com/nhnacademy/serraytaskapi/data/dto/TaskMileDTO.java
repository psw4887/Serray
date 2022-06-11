package com.nhnacademy.serraytaskapi.data.dto;

import java.time.LocalDate;

public interface TaskMileDTO {

    Integer getMileNo();

    String getContent();

    LocalDate getStart();

    LocalDate getEnd();
}
