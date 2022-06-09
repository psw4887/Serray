package com.nhnacademy.serrayaccountapi.service;

import com.nhnacademy.serrayaccountapi.data.dto.ForLoginDTO;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginVO;

public interface UserService {

    void userRegister(UserRegisterResponse response);

    ForLoginVO findUserById(String id);

    void userStateModify(String id, String state);
}
