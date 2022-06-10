package com.nhnacademy.serraytaskapi.service;

import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import java.util.List;

public interface ProjectService {

    List<PageableProjectResponse> getPageableProjectList(Integer page);

    void registerProject(ProjectRegisterVO vo);
}
