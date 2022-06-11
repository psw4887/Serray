package com.nhnacademy.serraytaskapi.service;

public interface MemberService {

    boolean isAdmin(Integer projectNo, String id);

    boolean isMember(Integer projectNo, String id);

    void registerMember(Integer projectNo, String id);

    void deleteMember(Integer projectNo, String id);
}
