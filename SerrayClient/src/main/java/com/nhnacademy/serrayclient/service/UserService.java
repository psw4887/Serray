package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.data.response.UserInfoResponse;

public interface UserService {

    UserInfoResponse getUser(String id);

    void RegisterUser(UserRegisterRequest request);

    void modifyUserState(String user, String state);
}
