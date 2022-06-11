package com.nhnacademy.serrayclient.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagModifyRequest {

    private Integer tagNo;

    private String content;
}
