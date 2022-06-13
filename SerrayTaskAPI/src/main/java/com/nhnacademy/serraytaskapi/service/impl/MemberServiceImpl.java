package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.entity.Member;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.repository.MemberRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProjectRepository pRepository;
    private final MemberRepository mRepository;

    @Override
    public boolean isAdmin(Integer projectNo, String id) {

        return pRepository.existsProjectByAdminAndProjectNo(id, projectNo);
    }

    @Override
    public boolean isMember(Integer projectNo, String id) {

        return mRepository.existsByMemberPK(new Member.MemberPK(id, projectNo));
    }

    @Transactional
    @Override
    public void registerMember(Integer projectNo, String id) {

        Member member = new Member(new Member.MemberPK(id, projectNo));

        member.setProject(pRepository.findById(projectNo).orElseThrow(ProjectNotFoundException::new));

        mRepository.save(member);
    }

    @Transactional
    @Override
    public void deleteMember(Integer projectNo, String id) {

        mRepository.deleteById(new Member.MemberPK(id, projectNo));
    }
}
