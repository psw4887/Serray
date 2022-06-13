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

import com.nhnacademy.serraytaskapi.data.dto.CommentDataDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectMileDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectTagDTO;
import com.nhnacademy.serraytaskapi.data.dto.TaskMileDTO;
import com.nhnacademy.serraytaskapi.data.dto.TaskModifyDataDTO;
import com.nhnacademy.serraytaskapi.data.dto.TaskTagDTO;
import com.nhnacademy.serraytaskapi.data.vo.TaskModifyVo;
import com.nhnacademy.serraytaskapi.data.vo.TaskRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.CommentRepository;
import com.nhnacademy.serraytaskapi.repository.MilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TagRepository;
import com.nhnacademy.serraytaskapi.repository.TaskMilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.repository.TaskTagRepository;
import com.nhnacademy.serraytaskapi.service.TaskService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TaskServiceImplTest {

    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    CommentRepository commentRepository;
    @MockBean
    TagRepository tagRepository;
    @MockBean
    MilestoneRepository milestoneRepository;
    @MockBean
    TaskTagRepository taskTagRepository;
    @MockBean
    TaskMilestoneRepository taskMilestoneRepository;

    @Autowired
    TaskService service;

    @DisplayName("업무 내용 가져오기 (마일스톤 X) (pageable)")
    @Test
    void getTaskDataNoMilestone() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Task task = new Task(1, project, "op", "제목", "내용");
        TaskModifyDataDTO taskModifyDataDTO = new TaskModifyDataDTO() {
            @Override
            public Integer getTaskNo() {
                return 1;
            }

            @Override
            public String getAdmin() {
                return "op";
            }

            @Override
            public String getTitle() {
                return "제목";
            }

            @Override
            public String getContent() {
                return "내용";
            }
        };

        List<CommentDataDTO> commentDataDTOS = List.of(new CommentDataDTO() {
            @Override
            public Integer getCommentNo() {
                return 1;
            }

            @Override
            public String getAdmin() {
                return "op";
            }

            @Override
            public String getContent() {
                return "내용";
            }
        });

        List<ProjectTagDTO> projectTagDTOS = List.of(new ProjectTagDTO() {
            @Override
            public Integer getTagNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }

            @Override
            public String getAdmin() {
                return "op";
            }
        });

        List<ProjectMileDTO> projectMileDTOS = List.of(new ProjectMileDTO() {
            @Override
            public Integer getMileNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }

            @Override
            public String getAdmin() {
                return "관리자";
            }
        });

        List<TaskTagDTO> taskTagDTOS = List.of(new TaskTagDTO() {
            @Override
            public Integer getTagNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }
        });

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(taskRepository.findByProjectNoForModifyData(anyInt())).thenReturn(taskModifyDataDTO);
        when(commentRepository.getCommentListByTaskNo(anyInt())).thenReturn(commentDataDTOS);
        when(tagRepository.findTagListByProjectNo(anyInt())).thenReturn(projectTagDTOS);
        when(milestoneRepository.findMileListByProjectNo(anyInt())).thenReturn(projectMileDTOS);
        when(taskTagRepository.findTaskTagList(anyInt())).thenReturn(taskTagDTOS);
        when(taskMilestoneRepository.existsByTask(any())).thenReturn(Boolean.FALSE);

        assertThat(service.getTaskData(1).getTaskMile()).isNull();
    }

    @DisplayName("업무 내용 가져오기 (마일스톤 O) (pageable)")
    @Test
    void getTaskDataYesMilestone() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Task task = new Task(1, project, "op", "제목", "내용");
        TaskModifyDataDTO taskModifyDataDTO = new TaskModifyDataDTO() {
            @Override
            public Integer getTaskNo() {
                return 1;
            }

            @Override
            public String getAdmin() {
                return "op";
            }

            @Override
            public String getTitle() {
                return "제목";
            }

            @Override
            public String getContent() {
                return "내용";
            }
        };

        List<CommentDataDTO> commentDataDTOS = List.of(new CommentDataDTO() {
            @Override
            public Integer getCommentNo() {
                return 1;
            }

            @Override
            public String getAdmin() {
                return "op";
            }

            @Override
            public String getContent() {
                return "내용";
            }
        });

        List<ProjectTagDTO> projectTagDTOS = List.of(new ProjectTagDTO() {
            @Override
            public Integer getTagNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }

            @Override
            public String getAdmin() {
                return "op";
            }
        });

        List<ProjectMileDTO> projectMileDTOS = List.of(new ProjectMileDTO() {
            @Override
            public Integer getMileNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }

            @Override
            public String getAdmin() {
                return "관리자";
            }
        });

        List<TaskTagDTO> taskTagDTOS = List.of(new TaskTagDTO() {
            @Override
            public Integer getTagNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "내용";
            }
        });

        TaskMileDTO taskMileDTO = new TaskMileDTO() {
            @Override
            public Integer getMileNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "content";
            }

            @Override
            public LocalDate getStart() {
                return LocalDate.now();
            }

            @Override
            public LocalDate getEnd() {
                return LocalDate.now();
            }
        };

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(taskRepository.findByProjectNoForModifyData(anyInt())).thenReturn(taskModifyDataDTO);
        when(commentRepository.getCommentListByTaskNo(anyInt())).thenReturn(commentDataDTOS);
        when(tagRepository.findTagListByProjectNo(anyInt())).thenReturn(projectTagDTOS);
        when(milestoneRepository.findMileListByProjectNo(anyInt())).thenReturn(projectMileDTOS);
        when(taskTagRepository.findTaskTagList(anyInt())).thenReturn(taskTagDTOS);
        when(taskMilestoneRepository.existsByTask(any())).thenReturn(Boolean.TRUE);
        when(taskMilestoneRepository.findTaskMile(anyInt())).thenReturn(taskMileDTO);

        assertThat(service.getTaskData(1).getTaskMile()).isNotNull();
    }

    @DisplayName("업무 내용 가져오기 (업무 X) (pageable)")
    @Test
    void getTaskDataNoTask() {

        assertThatThrownBy(() -> service.getTaskData(99))
            .isInstanceOf(TaskNotFoundException.class);
    }

    @DisplayName("업무 등록하기")
    @Test
    void registerTask() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Task task = new Task(project, "op", "제목", "내용");

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));

        service.registerTask(new TaskRegisterVO(2, "op", "제목", "내용"));

        assertThat(taskRepository.findById(2)).isPresent();
    }

    @DisplayName("업무 수정하기")
    @Test
    void modifyTask() {

        Project project = new Project(10, "op", "제목", "내용", "활성");

        Task task = new Task(101, project, "op", "제목", "내용");
        taskRepository.saveAndFlush(task);

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));

        service.modifyTask(new TaskModifyVo(task.getTaskNo(), "목제", "용내"));

        assertThat(taskRepository.findById(100).get().getTitle()).isEqualTo("목제");
    }

    @DisplayName("업무 삭제하기")
    @Test
    void deleteTask() {

        doNothing().when(commentRepository).deleteByTaskNo(anyInt());
        doNothing().when(taskTagRepository).deleteByTaskNo(anyInt());
        doNothing().when(taskMilestoneRepository).deleteByTaskNo(anyInt());
        doNothing().when(taskRepository).deleteById(anyInt());

        service.deleteTask(1);

        verify(commentRepository, atLeastOnce()).deleteByTaskNo(anyInt());
        verify(taskTagRepository, atLeastOnce()).deleteByTaskNo(anyInt());
        verify(taskMilestoneRepository, atLeastOnce()).deleteByTaskNo(anyInt());
        verify(taskRepository, atLeastOnce()).deleteById(anyInt());
    }
}