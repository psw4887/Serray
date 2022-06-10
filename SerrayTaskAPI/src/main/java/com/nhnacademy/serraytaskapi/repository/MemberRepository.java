package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Member.MemberPK> {


    Boolean existsByMemberPK(Member.MemberPK pk);
}
