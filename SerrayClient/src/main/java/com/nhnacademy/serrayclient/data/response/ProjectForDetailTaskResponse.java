package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectForDetailTaskResponse {

    private Integer taskNo;

    private String admin;

    private String title;
}
