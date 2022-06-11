package com.nhnacademy.serrayclient.service;

public interface MilestoneService {

    void registerMilestone(Integer projectNo, String content, String admin);

    void modifyMilestone(Integer mileNo, String content);

    void deleteMilestone(Integer mileNo);
}
