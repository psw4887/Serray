package com.nhnacademy.serrayclient.service;

public interface MemberService {
    boolean isProjectAdmin(Integer projectNo, String id);

    boolean isProjectMember(Integer projectNo, String id);

    void registerMember(Integer projectNo, String id);

    void deleteMember(Integer projectNo, String id);
}
