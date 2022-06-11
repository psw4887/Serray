package com.nhnacademy.serraytaskapi.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDataResponse {

    private Integer taskNo;

    private String admin;

    private String title;

    private String content;

    private List<CommentDataResponse> comments;
}
