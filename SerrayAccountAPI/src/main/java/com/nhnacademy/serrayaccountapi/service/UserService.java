package com.nhnacademy.serrayaccountapi.service;

import com.nhnacademy.serrayaccountapi.data.response.OnlyUserIdResponse;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;

import java.util.List;

public interface UserService {

    List<OnlyUserIdResponse> getUserListStateOK();

    void userRegister(UserRegisterResponse response);

    ForLoginUserVO findUserById(String id);

    void userStateModify(String id, String state);

    ForLoginUserVO findUserByEmail(String email);
}
