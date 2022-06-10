package com.nhnacademy.serraytaskapi.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRegisterVO {

    private Integer projectNo;

    private String id;

    private String title;

    private String content;
}
