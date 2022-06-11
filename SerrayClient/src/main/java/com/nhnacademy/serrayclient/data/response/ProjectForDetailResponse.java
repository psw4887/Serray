package com.nhnacademy.serrayclient.data.response;

import java.util.List;
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

    private List<ProjectForDetailTaskResponse> tasks;

    private List<ProjectForDetailMemberResponse> members;

    private List<ProjectForDetailTagResponse> tags;

    private List<ProjectForDetailMileResponse> miles;
}
