package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.PageableProjectDTO;
import com.nhnacademy.serraytaskapi.data.response.PageableProjectResponse;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository pRepository;

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
}
