package com.nhnacademy.serrayclient.service;

import com.nhnacademy.serrayclient.data.response.ProjectForListResponse;
import java.util.List;

public interface ProjectService {

    List<ProjectForListResponse> getProjectList(Integer page);
}
