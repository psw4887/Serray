package com.nhnacademy.serrayaccountapi.service;

import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;

public interface UserService {

    void userRegister(UserRegisterResponse response);

    ForLoginUserVO findUserById(String id);

    void userStateModify(String id, String state);
}
