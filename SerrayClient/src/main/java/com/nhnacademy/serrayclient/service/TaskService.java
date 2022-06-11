package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.response.TaskDataResponse;

public interface TaskService {

    TaskDataResponse getTaskData(Integer taskNo);

    void registerTask(Integer projectNo, String id, String title, String content);

    void modifyTask(Integer taskNo, String title, String content);

    void deleteTask(Integer taskNo);

}
