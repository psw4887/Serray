package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.request.ProjectRegisterRequest;
import com.nhnacademy.serrayclient.data.response.ProjectForDetailResponse;
import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import java.util.List;

public interface ProjectService {

    List<ProjectForListResponse> getProjectList(Integer page);

    void registerProject(ProjectRegisterRequest projectRegisterRequest);

    ProjectForDetailResponse detailProject(Integer projectNo, Integer page);

    void projectModifyState(Integer projectNo, String state);
}
