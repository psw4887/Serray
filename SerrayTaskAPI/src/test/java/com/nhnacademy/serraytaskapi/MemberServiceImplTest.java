package com.nhnacademy.serraytaskapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.serraytaskapi.entity.Member;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.repository.MemberRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.service.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService service;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    MemberRepository memberRepository;

    @DisplayName("멤버가 관리자인가?")
    @Test
    void isAdmin() {

        when(projectRepository.existsProjectByAdminAndProjectNo(anyString(), anyInt())).thenReturn(true);

        assertThat(service.isAdmin(1, "op")).isTrue();
    }

    @DisplayName("멤버가 프로젝트 멤버인가?")
    @Test
    void isMember() {

        when(memberRepository.existsByMemberPK(any())).thenReturn(true);


        assertThat(service.isMember(1, "op")).isTrue();
    }

    @DisplayName("멤버 추가하기")
    @Test
    void registerMember() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Member member = new Member(new Member.MemberPK("op" , 2));

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));

        service.registerMember(1, "op");

        assertThat(memberRepository.findById(new Member.MemberPK("op", 2))).isPresent();
        verify(memberRepository, atLeastOnce()).save(any());
    }

    @DisplayName("멤버 삭제하기")
    @Test
    void deleteMember() {

        doNothing().when(memberRepository).deleteById(any());

        service.deleteMember(1, "op");

        verify(memberRepository, atLeastOnce()).deleteById(any());
    }
}