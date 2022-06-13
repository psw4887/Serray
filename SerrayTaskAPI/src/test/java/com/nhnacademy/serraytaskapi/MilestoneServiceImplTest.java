package com.nhnacademy.serraytaskapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.serraytaskapi.data.vo.MileModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.MileRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;
import com.nhnacademy.serraytaskapi.data.vo.TaskMileRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Milestone;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Tag;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.entity.TaskMilestone;
import com.nhnacademy.serraytaskapi.entity.TaskTag;
import com.nhnacademy.serraytaskapi.exception.MilestoneNotFoundException;
import com.nhnacademy.serraytaskapi.repository.MilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TaskMilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.MilestoneService;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MilestoneServiceImplTest {

    @Autowired
    MilestoneService service;

    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    MilestoneRepository milestoneRepository;
    @MockBean
    TaskMilestoneRepository taskMilestoneRepository;

    @DisplayName("프로젝트 마일스톤 추가")
    @Test
    void projectMileRegister() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Milestone milestone = new Milestone(project, "태그", "op");

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(milestoneRepository.findById(anyInt())).thenReturn(Optional.of(milestone));

        service.projectMileRegister(new MileRegisterVO(1, "내용", "op"));
        milestoneRepository.flush();

        assertThat(milestoneRepository.findById(2)).isEqualTo(Optional.of(milestone));
        verify(milestoneRepository, atLeastOnce()).save(any());
    }

    @DisplayName("프로젝트 마일스톤 수정")
    @Test
    void projectMileModify() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Milestone milestone = new Milestone(project, "태그", "op");

        when(milestoneRepository.findById(anyInt())).thenReturn(Optional.of(milestone));

        service.projectMileModify(new MileModifyVO(1, "용내"));
        milestoneRepository.flush();

        assertThat(milestoneRepository.findById(2).get().getContent()).isEqualTo("용내");
    }

    @DisplayName("프로젝트 마일스톤 수정 (마일스톤 X)")
    @Test
    void projectMileModifyNoMile() {

        assertThatThrownBy(()->service.projectMileModify(new MileModifyVO(1, "어드민")))
            .isInstanceOf(MilestoneNotFoundException.class);
    }

    @DisplayName("프로젝트 마일스톤 삭제")
    @Test
    void projectMileDelete() {

        doNothing().when(taskMilestoneRepository).deleteByMileNo(anyInt());
        doNothing().when(milestoneRepository).deleteById(anyInt());

        service.projectMileDelete(1);

        verify(taskMilestoneRepository, atLeastOnce()).deleteByMileNo(anyInt());
        verify(milestoneRepository, atLeastOnce()).deleteById(anyInt());
    }

    @DisplayName("업무 마일스톤 등록")
    @Test
    void taskMileRegister() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Milestone milestone = new Milestone(project, "태그", "op");
        Task task = new Task(1, project, "op", "제목", "내용");

        TaskMilestone.TaskMilestonePK pk = new TaskMilestone.TaskMilestonePK(1, 1);
        TaskMilestone taskMilestone = new TaskMilestone(pk, milestone, task, LocalDate.now(), LocalDate.now());

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(milestoneRepository.findById(anyInt())).thenReturn(Optional.of(milestone));

        service.taskMileRegister(new TaskMileRegisterVO(1, 1 ,LocalDate.now(), LocalDate.now()));

        verify(taskMilestoneRepository, atLeastOnce()).save(taskMilestone);
    }
}