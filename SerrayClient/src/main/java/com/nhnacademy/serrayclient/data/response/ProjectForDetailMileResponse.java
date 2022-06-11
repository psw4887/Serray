package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectForDetailMileResponse {

    private Integer mileNo;

    private String content;

    private String admin;
}
