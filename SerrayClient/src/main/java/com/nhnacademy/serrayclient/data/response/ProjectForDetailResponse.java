package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectForDetailResponse {

    private String id;

    private String title;

    private String content;

    private String state;
}
