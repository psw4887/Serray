package com.nhnacademy.serrayclient.service;

import java.time.LocalDate;

public interface MilestoneService {

    void registerMilestone(Integer projectNo, String content, String admin);

    void modifyMilestone(Integer mileNo, String content);

    void deleteMilestone(Integer mileNo);

    void addTaskMile(Integer mileNo, Integer taskNo, LocalDate start, LocalDate end);
}
