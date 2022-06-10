package com.nhnacademy.serraytaskapi.data.response;

import java.util.List;
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

    private List<ProjectDetailTaskResponse> tasks;
}
