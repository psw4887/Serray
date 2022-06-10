package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import java.util.Collection;
import java.util.Optional;

public interface UserService {

    UserInfoResponse getUser(String id);

    void RegisterUser(UserRegisterRequest request);

    void modifyUserState(String user, String state);

    UserInfoResponse findByUserEmail(String email);
}
