package com.nhnacademy.serrayaccountapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import com.nhnacademy.serrayaccountapi.data.dto.ForLoginDTO;
import com.nhnacademy.serrayaccountapi.data.dto.OnlyUserIdDTO;
import com.nhnacademy.serrayaccountapi.data.response.UserRegisterResponse;
import com.nhnacademy.serrayaccountapi.data.vo.ForLoginUserVO;
import com.nhnacademy.serrayaccountapi.entity.User;
import com.nhnacademy.serrayaccountapi.exception.UserNotFoundException;
import com.nhnacademy.serrayaccountapi.repository.UserRepository;
import com.nhnacademy.serrayaccountapi.service.UserService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @MockBean
    UserRepository repository;

    @Autowired
    UserService service;

    @DisplayName("상태 '가입'인 사람 리스트 가져오기")
    @Test
    void getUserListStateOK() {

        List<OnlyUserIdDTO> dtoS = List.of(() -> "op");

        when(repository.getUserIdsByStateIsOK()).thenReturn(dtoS);
        assertThat(service.getUserListStateOK().get(0).getId()).isEqualTo("op");
    }

    @DisplayName("회원 가입 성공")
    @Test
    void userRegister() {

        when(repository.getUserByUserId(anyString())).thenReturn(isNull());
        UserRegisterResponse response = new UserRegisterResponse("op", "12", "p@c.com");

        service.userRegister(response);

        repository.flush();

        assertThat(repository.findById(3)).isNotNull();

    }

    @DisplayName("회원가입 실패")
    @Test
    void userRegisterFail() {

        when(repository.getUserByUserId(anyString())).thenReturn(notNull());
        UserRegisterResponse response = new UserRegisterResponse("op", "12", "p@c.com");

        service.userRegister(response);

        assertThat(repository.getUserByUserId("op")).isNull();

    }

    @DisplayName("사용자 아이디로 사용자 찾기")
    @Test
    void findUserById() {

        ForLoginDTO dto = new ForLoginDTO() {
            @Override
            public String getUserId() {
                return "op";
            }

            @Override
            public String getUserPw() {
                return "123";
            }

            @Override
            public String getUserEmail() {
                return "p.@naver.com";
            }

            @Override
            public String getUserState() {
                return "가입";
            }
        };

        when(repository.getUserForLogin(anyString())).thenReturn(Optional.of(dto));

        assertThat(service.findUserById("op")).isEqualTo(
            new ForLoginUserVO(dto.getUserId(), dto.getUserPw(), dto.getUserEmail(), dto.getUserState()));
    }

    @DisplayName("사용자 아이디로 사용자 찾기실패")
    @Test
    void findUserByIdFail() {

        assertThatThrownBy(() -> service.findUserById("xx"))
            .isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("사용자 이메일로 사용자 찾기")
    @Test
    void findUserByEmail() {

        ForLoginDTO dto = new ForLoginDTO() {
            @Override
            public String getUserId() {
                return "op";
            }

            @Override
            public String getUserPw() {
                return "123";
            }

            @Override
            public String getUserEmail() {
                return "p@naver.com";
            }

            @Override
            public String getUserState() {
                return "가입";
            }
        };

        when(repository.getUserForGitLogin(anyString())).thenReturn(dto);

        assertThat(service.findUserByEmail("p@naver.com")).isEqualTo(new ForLoginUserVO(
           dto.getUserId(), dto.getUserPw(), dto.getUserEmail(), dto.getUserState()));
    }

    @DisplayName("사용자 상태 가입->휴면 변경")
    @Test
    void userStateModify() {

        User user = new User("op", "123", "p@naver.com", "가입");
        repository.save(user);

        when(repository.getUserByUserId(anyString())).thenReturn(user);
        service.userStateModify("op", "휴면");

        repository.flush();

        assertThat(repository.getUserByUserId("op").getUserState()).isEqualTo("휴면");
    }

    @DisplayName("사용자 상태 가입->탈퇴 변경 (삭제)")
    @Test
    void userStateModifyInDelete() {

        User user = new User(1000, "op", "123", "p@naver.com", "가입");
        repository.save(user);

        when(repository.getUserByUserId(anyString())).thenReturn(user);

        service.userStateModify("op", "탈퇴");

        repository.flush();

        assertThat(repository.findById(1000)).isEmpty();
    }
}