package com.nhnacademy.serraytaskapi.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailTaskResponse {

    private Integer taskNo;

    private String admin;

    private String title;
}
