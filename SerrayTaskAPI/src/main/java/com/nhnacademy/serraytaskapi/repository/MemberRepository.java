package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.OnlyMemberIdDTO;
import com.nhnacademy.serraytaskapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Member.MemberPK> {

    Boolean existsByMemberPK(Member.MemberPK pk);

    @Query("select m.memberPK.memberId as memberId from Member m where m.project.projectNo = ?1")
    List<OnlyMemberIdDTO> findByProjectNo(Integer projectNo);
}
