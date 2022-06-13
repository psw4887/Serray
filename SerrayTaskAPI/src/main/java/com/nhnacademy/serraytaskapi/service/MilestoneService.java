package com.nhnacademy.serraytaskapi.service;


import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TaskMileRegisterVO;

public interface MilestoneService {

    void projectMileRegister(MileRegisterVO vo);

    void projectMileModify(MileModifyVO vo);

    void projectMileDelete(Integer mileNo);

    void taskMileRegister(TaskMileRegisterVO vo);
}
