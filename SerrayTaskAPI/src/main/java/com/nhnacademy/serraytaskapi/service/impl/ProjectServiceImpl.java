package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.PageableProjectDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailTaskDTO;
import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.response.ProjectDetailResponse;
import com.nhnacademy.serraytaskapi.data.response.ProjectDetailTaskResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;

    @Override
    public List<PageableProjectResponse> getPageableProjectList(Integer page) {

        PageRequest pageRequest = PageRequest.of(page , 10);

        List<PageableProjectDTO> dto = pRepository.getAllBy(pageRequest).getContent();
        List<PageableProjectResponse> responses = new ArrayList<>();

        for (PageableProjectDTO pageableProjectDTO : dto) {
            PageableProjectResponse response = new PageableProjectResponse(
                pageableProjectDTO.getProjectNo(),
                pageableProjectDTO.getAdmin(),
                pageableProjectDTO.getTitle(),
                pageableProjectDTO.getState());

            responses.add(response);
        }

        return responses;
    }

    @Transactional
    @Override
    public void registerProject(ProjectRegisterVO vo) {

        Project project = new Project(vo.getId(), vo.getTitle(), vo.getContent(), "활성");

        pRepository.save(project);
    }

    @Override
    public ProjectDetailResponse getDetailProject(Integer page, Integer projectNo) {

        PageRequest pageRequest = PageRequest.of(page, 5);

        ProjectDetailDTO dto = pRepository.findByProjectNo(projectNo).orElseThrow(ProjectNotFoundException::new);
        List<ProjectDetailTaskDTO> taskDTOs = tRepository.findByProjectNo(pageRequest, projectNo).getContent();
        List<ProjectDetailTaskResponse> responses = new ArrayList<>();

        for (ProjectDetailTaskDTO taskDTO:taskDTOs) {
            ProjectDetailTaskResponse response = new ProjectDetailTaskResponse(
                taskDTO.getTaskNo(), taskDTO.getAdmin(), taskDTO.getTitle()
            );
            responses.add(response);
        }

        return new ProjectDetailResponse(dto.getAdmin(), dto.getTitle(),
            dto.getContent(), dto.getState(), responses);
    }
}
