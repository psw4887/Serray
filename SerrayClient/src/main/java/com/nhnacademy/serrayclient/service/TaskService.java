package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.response.TaskModifyDataResponse;

public interface TaskService {

    TaskModifyDataResponse getTaskForModifyData(Integer taskNo);

    void registerTask(Integer projectNo, String id, String title, String content);

    void modifyTask(Integer taskNo, String title, String content);

    void deleteTask(Integer taskNo);

}
