package com.nhnacademy.serraytaskapi.service;

import com.nhnacademy.serraytaskapi.data.response.TaskDataResponse;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;

public interface TaskService {

    TaskDataResponse getTaskData(Integer taskNo);

    void registerTask(TaskRegisterVO vo);

    void modifyTask(TaskModifyVo vo);

    void deleteTask(Integer taskNo);
}
