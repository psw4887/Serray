package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Milestone;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.exception.MilestoneNotFoundException;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.repository.MilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TaskMilestoneRepository;
import com.nhnacademy.serraytaskapi.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final ProjectRepository pRepository;
    private final MilestoneRepository mileRepository;
    private final TaskMilestoneRepository taskMileRepository;

    @Transactional
    @Override
    public void projectMileRegister(MileRegisterVO vo) {

        Project project = pRepository.findById(vo.getProjectNo()).orElseThrow(ProjectNotFoundException::new);
        Milestone milestone = new Milestone(project, vo.getContent(), vo.getAdmin());

        mileRepository.save(milestone);
    }

    @Transactional
    @Override
    public void projectMileModify(MileModifyVO vo) {

        Milestone milestone = mileRepository.findById(vo.getMileNo()).orElseThrow(MilestoneNotFoundException::new);

        milestone.setContent(vo.getContent());

        mileRepository.save(milestone);
    }

    @Transactional
    @Override
    public void projectMileDelete(Integer mileNo) {

        mileRepository.deleteById(mileNo);
    }
}
