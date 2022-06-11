package com.nhnacademy.serrayclient.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDataResponse {

    private String taskNo;

    private String admin;

    private String title;

    private String content;

    private List<CommentDataResponse> comments;
}
