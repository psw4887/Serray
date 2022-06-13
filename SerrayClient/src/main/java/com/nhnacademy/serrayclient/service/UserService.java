package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.request.UserRegisterRequest;
import com.nhnacademy.serrayclient.data.response.UserIdResponse;
import com.nhnacademy.serrayclient.data.response.UserInfoResponse;
import java.util.List;

public interface UserService {

    UserInfoResponse getUser(String id);

    List<UserIdResponse> getUsersForStateOK();

    void registerUser(UserRegisterRequest request);

    void modifyUserState(String user, String state);

    UserInfoResponse findByUserEmail(String email);
}
