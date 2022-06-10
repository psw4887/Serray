package com.nhnacademy.serraytaskapi.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailResponse {

    private String id;

    private String title;

    private String content;

    private String state;
}
