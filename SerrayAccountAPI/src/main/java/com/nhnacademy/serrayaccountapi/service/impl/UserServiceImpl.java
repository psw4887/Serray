package com.nhnacademy.serrayaccountapi.service.impl;

import com.nhnacademy.serrayaccountapi.data.dto.ForLoginDTO;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;
import com.nhnacademy.serrayaccountapi.entity.User;
import com.nhnacademy.serrayaccountapi.repository.UserRepository;
import com.nhnacademy.serrayaccountapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository uRepository;

    @Transactional
    @Override
    public void userRegister(UserRegisterResponse response) {

        User user = new User(response.getId(), response.getPw(), response.getEmail(), "가입");

        uRepository.save(user);
    }

    @Override
    public ForLoginUserVO findUserById(String id) {

        ForLoginDTO dto = uRepository.getUserForLogin(id);

        return new ForLoginUserVO(dto.getUserId(), dto.getUserPw(), dto.getUserEmail(), dto.getUserState());
    }

    @Override
    public ForLoginUserVO findUserByEmail(String email) {

        ForLoginDTO dto = uRepository.getUserForGitLogin(email);

        return new ForLoginUserVO(dto.getUserId(), dto.getUserPw(), dto.getUserEmail(), dto.getUserState());
    }

    @Transactional
    @Override
    public void userStateModify(String id, String state) {

        User user = uRepository.getUserByUserId(id);

        if(state.equals("탈퇴")) {
            uRepository.deleteById(user.getUserNo());
            return;
        }

        user.setUserState(state);

        uRepository.save(user);
    }
}
