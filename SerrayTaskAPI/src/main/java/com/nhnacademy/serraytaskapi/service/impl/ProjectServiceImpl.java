package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.dto.*;
import com.nhnacademy.serraytaskapi.data.response.*;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Member;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.repository.*;
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

    private final MemberRepository mRepository;
    private final ProjectRepository pRepository;
    private final TaskRepository tRepository;
    private final TagRepository tagRepository;
    private final MilestoneRepository mileRepository;

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

        Member.MemberPK memberPK = new Member.MemberPK(vo.getId(), project.getProjectNo());
        Member member = new Member(memberPK);
        member.setProject(project);

        mRepository.save(member);
    }

    @Override
    public ProjectDetailResponse getDetailProject(Integer page, Integer projectNo) {

        PageRequest pageRequest = PageRequest.of(page, 5);
        ProjectDetailDTO dto = pRepository.findByProjectNo(projectNo).orElseThrow(ProjectNotFoundException::new);

        List<ProjectDetailTaskDTO> taskDTOs = tRepository.findByProjectNo(pageRequest, projectNo).getContent();
        List<OnlyMemberIdDTO> members = mRepository.findByProjectNo(projectNo);
        List<ProjectTagDTO> tagDTOs = tagRepository.findTagListByProjectNo(projectNo);
        List<ProjectMileDTO> mileDTOs = mileRepository.findMileListByProjectNo(projectNo);

        List<ProjectDetailTaskResponse> tResponses = new ArrayList<>();
        List<ProjectDetailMemberResponse> mResponses = new ArrayList<>();
        List<ProjectDetailTagResponse> tags = new ArrayList<>();
        List<ProjectDetailMileResponse> miles = new ArrayList<>();

        for (ProjectDetailTaskDTO taskDTO:taskDTOs) {
            tResponses.add(new ProjectDetailTaskResponse(
                    taskDTO.getTaskNo(), taskDTO.getAdmin(), taskDTO.getTitle()
            ));
        }

        for (OnlyMemberIdDTO memberDTO:members) {
            mResponses.add(new ProjectDetailMemberResponse(
                    memberDTO.getMemberId()
            ));
        }

        for (ProjectTagDTO tagDTO:tagDTOs) {
            tags.add(new ProjectDetailTagResponse(
                    tagDTO.getTagNo(), tagDTO.getContent(), tagDTO.getAdmin()
            ));
        }

        for (ProjectMileDTO mileDTO:mileDTOs) {
            miles.add(new ProjectDetailMileResponse(
                    mileDTO.getMileNo(), mileDTO.getContent(), mileDTO.getAdmin()
            ));
        }

        return new ProjectDetailResponse(dto.getAdmin(), dto.getTitle(),
            dto.getContent(), dto.getState(), tResponses, mResponses, tags, miles);
    }

    @Transactional
    @Override
    public void modifyStateProject(Integer projectNo, String state) {

        Project project = pRepository.findById(projectNo).orElseThrow(ProjectNotFoundException::new);
        project.setState(state);

        pRepository.save(project);
    }
}
