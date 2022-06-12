package com.nhnacademy.serrayclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRegisterRequest {

    private Integer taskNo;

    private String admin;

    private String content;
}
