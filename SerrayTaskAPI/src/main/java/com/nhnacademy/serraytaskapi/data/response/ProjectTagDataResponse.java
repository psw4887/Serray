package com.nhnacademy.serraytaskapi.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTagDataResponse {

    private Integer tagNo;

    private String content;
}
