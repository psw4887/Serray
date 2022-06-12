package com.nhnacademy.serrayclient.data.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "ID는 공백일 수 없습니다.")
    @Length(min = 1, max = 20, message = "ID는 1~20 글자 사이여야합니다.")
    private String id;

    @NotBlank(message = "PASSWORD 는 공백일 수 없습니다.")
    @Length(min = 1, max = 20, message = "PASSWORD 는 1~20 글자 사이여야합니다.")
    private String pw;

    @NotBlank(message = "EMAIL 은 공백일 수 없습니다.")
    @Email(message = "EMAIL 은 이메일 형식으로 작성하여야합니다.")
    private String email;
}
