package com.nhnacademy.serraytaskapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.nhnacademy.serraytaskapi.data.dto.OnlyMemberIdDTO;
import com.nhnacademy.serraytaskapi.data.dto.PageableProjectDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailTaskDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectMileDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectTagDTO;
import com.nhnacademy.serraytaskapi.data.vo.ProjectRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.serraytaskapi.repository.MemberRepository;
import com.nhnacademy.serraytaskapi.repository.MilestoneRepository;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TagRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.ProjectService;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ProjectServiceImplTest {

    @MockBean
    ProjectRepository repository;

    @MockBean
    MemberRepository memberRepository;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    TagRepository tagRepository;
    @MockBean
    MilestoneRepository milestoneRepository;

    @Autowired
    ProjectService service;

    @DisplayName("프로젝트 목록 가져오기 (pageable)")
    @Test
    void getPageableProjectList() {

        Page<PageableProjectDTO> dto = new Page<>() {
            @Override
            public int getTotalPages() {
                return 0;
            }
            @Override
            public long getTotalElements() {
                return 1;
            }
            @Override
            public <U> Page<U> map(Function<? super PageableProjectDTO, ? extends U> converter) {
                return null;
            }
            @Override
            public int getNumber() {
                return 0;
            }
            @Override
            public int getSize() {
                return 1;
            }
            @Override
            public int getNumberOfElements() {
                return 1;
            }
            @Override
            public List<PageableProjectDTO> getContent() {
                return List.of(new PageableProjectDTO() {
                    @Override
                    public Integer getProjectNo() {
                        return 9;
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
                    public String getState() {
                        return "종료";
                    }
                });
            }
            @Override
            public boolean hasContent() {
                return false;
            }
            @Override
            public Sort getSort() {
                return null;
            }
            @Override
            public boolean isFirst() {
                return false;
            }
            @Override
            public boolean isLast() {
                return false;
            }
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public boolean hasPrevious() {
                return false;
            }
            @Override
            public Pageable nextPageable() {
                return null;
            }
            @Override
            public Pageable previousPageable() {
                return null;
            }
            @Override
            public Iterator<PageableProjectDTO> iterator() {
                return null;
            }
        };

        when(repository.getAllBy(any())).thenReturn(dto);

        assertThat(service.getPageableProjectList(0).get(0).getAdmin()).isEqualTo("op");
    }

    @DisplayName("프로젝트 생성")
    @Test
    void registerProject() {

        ProjectRegisterVO vo = new ProjectRegisterVO("op", "제목", "content");
        Project project = new Project(1, vo.getAdmin(), vo.getTitle(), vo.getContent(), "활성");

        service.registerProject(vo);

        repository.flush();
        memberRepository.flush();

        when(repository.findById(anyInt())).thenReturn(Optional.of(project));

        assertThat(repository.findById(1).get().getAdmin()).isEqualTo("op");
    }

    @DisplayName("프로젝트 내용 가져오기")
    @Test
    void getDetailProject() {

        ProjectDetailDTO dto = new ProjectDetailDTO() {
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

            @Override
            public String getState() {
                return "활성";
            }
        };

        Page<ProjectDetailTaskDTO> detailTaskDTOS = new Page<>() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 1;
            }

            @Override
            public <U> Page<U> map(Function<? super ProjectDetailTaskDTO, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 1;
            }

            @Override
            public int getNumberOfElements() {
                return 1;
            }

            @Override
            public List<ProjectDetailTaskDTO> getContent() {
                return List.of(new ProjectDetailTaskDTO() {
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
                });
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<ProjectDetailTaskDTO> iterator() {
                return null;
            }
        };

        List<OnlyMemberIdDTO> memberList = List.of(() -> "op");

        List<ProjectTagDTO> tagList = List.of(new ProjectTagDTO() {
            @Override
            public Integer getTagNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "content";
            }

            @Override
            public String getAdmin() {
                return "op";
            }
        });

        List<ProjectMileDTO> mileList = List.of(new ProjectMileDTO() {
            @Override
            public Integer getMileNo() {
                return 1;
            }

            @Override
            public String getContent() {
                return "content";
            }

            @Override
            public String getAdmin() {
                return "op";
            }
        });

        when(repository.findByProjectNo(anyInt())).thenReturn(Optional.of(dto));
        when(taskRepository.findByProjectNo(any(), anyInt())).thenReturn(detailTaskDTOS);
        when(memberRepository.findByProjectNo(anyInt())).thenReturn(memberList);
        when(tagRepository.findTagListByProjectNo(anyInt())).thenReturn(tagList);
        when(milestoneRepository.findMileListByProjectNo(anyInt())).thenReturn(mileList);

        assertThat(service.getDetailProject(0, 1).getTitle()).isEqualTo("제목");
    }

    @DisplayName("프로젝트 상태 수정하기")
    @Test
    void modifyStateProject() {

        Project project = new Project(1000, "op", "title", "content", "활성");

        when(repository.findById(anyInt())).thenReturn(Optional.of(project));
        service.modifyStateProject(1000, "종료");
        repository.flush();

        assertThat(repository.findById(1000).get().getState()).isEqualTo("종료");
    }

    @DisplayName("프로젝트 찾기 불가")
    @Test
    void projectNotFound() {

        assertThatThrownBy(() -> service.modifyStateProject(9, "활성"))
            .isInstanceOf(ProjectNotFoundException.class);
    }
}