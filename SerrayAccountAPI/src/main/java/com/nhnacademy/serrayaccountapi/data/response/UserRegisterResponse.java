package com.nhnacademy.serrayaccountapi.data.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class UserRegisterResponse {

    @NotBlank(message = "ID는 공백일 수 없습니다.")
    @Length(min = 1, max= 20, message = "ID는 1~20글자여야합니다.")
    private String id;

    @NotBlank
    @Length(min = 1, max = 300, message = "pw는 1~300글자여야합니다.")
    private String pw;

    @NotBlank(message = "EMAIL 은 공백일 수 없습니다.")
    @Email(message = "EMAIL은 이메일형식에 맞게 작성해주셔야합니다.")
    private String email;
}
