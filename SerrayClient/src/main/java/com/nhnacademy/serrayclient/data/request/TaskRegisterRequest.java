package com.nhnacademy.serrayclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRegisterRequest {

    private Integer projectNo;

    private String id;

    private String title;

    private String content;
}
