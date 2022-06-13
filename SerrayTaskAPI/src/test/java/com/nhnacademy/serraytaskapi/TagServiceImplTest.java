package com.nhnacademy.serraytaskapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.serraytaskapi.data.vo.TagModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.TagRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Tag;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.entity.TaskTag;
import com.nhnacademy.serraytaskapi.exception.TagNotFoundException;
import com.nhnacademy.serraytaskapi.repository.ProjectRepository;
import com.nhnacademy.serraytaskapi.repository.TagRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.repository.TaskTagRepository;
import com.nhnacademy.serraytaskapi.service.TagService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TagServiceImplTest {

    @Autowired
    TagService service;

    @MockBean
    ProjectRepository projectRepository;
    @MockBean
    TaskRepository taskRepository;
    @MockBean
    TagRepository tagRepository;
    @MockBean
    TaskTagRepository taskTagRepository;

    @DisplayName("프로젝트 태그 추가")
    @Test
    void projectTagRegister() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Tag tag = new Tag(project, "태그", "op");

        when(projectRepository.findById(anyInt())).thenReturn(Optional.of(project));
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tag));

        service.projectTagRegister(new TagRegisterVO(1, "내용", "op"));
        tagRepository.flush();

        assertThat(tagRepository.findById(2)).isEqualTo(Optional.of(tag));
    }

    @DisplayName("프로젝트 태그 수정")
    @Test
    void projectTagModify() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Tag tag = new Tag(project, "태그", "op");

        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tag));

        service.projectTagModify(new TagModifyVO(1, "용내"));

        tagRepository.flush();

        assertThat(tagRepository.findById(2).get().getContent()).isEqualTo("용내");
    }

    @DisplayName("프로젝트 태그 삭제")
    @Test
    void projectTagDelete() {

        doNothing().when(taskTagRepository).deleteByTagNo(anyInt());
        doNothing().when(tagRepository).deleteById(anyInt());

        service.projectTagDelete(1);

        verify(taskTagRepository, atLeastOnce()).deleteByTagNo(anyInt());
        verify(tagRepository, atLeastOnce()).deleteById(anyInt());
    }

    @DisplayName("업무 태그 추가 (중복 O)")
    @Test
    void taskTagRegister() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Tag tag = new Tag(project, "태그", "op");
        Task task = new Task(1, project, "op", "제목", "내용");

        TaskTag.TaskTagPK pk = new TaskTag.TaskTagPK(1, 2);
        TaskTag taskTag = new TaskTag(pk, tag, task);

        when(taskTagRepository.findById(any())).thenReturn(Optional.of(taskTag));

        service.taskTagRegister(1, 2);
        taskTagRepository.flush();

        verify(taskTagRepository, times(0)).save(taskTag);
    }

    @DisplayName("업무 태그 추가 (중복 X)")
    @Test
    void taskTagRegisterDistinct() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Tag tag = new Tag(project, "dd", "xx");
        Task task = new Task(1, project, "op", "제목", "내용");

        TaskTag.TaskTagPK pk = new TaskTag.TaskTagPK(1, 1);
        TaskTag taskTag = new TaskTag(pk, tag, task);

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(tagRepository.findById(anyInt())).thenReturn(Optional.of(tag));

        service.taskTagRegister(1, 1);

        verify(taskTagRepository, atLeastOnce()).save(taskTag);
    }

    @DisplayName("업무 태그 추가 (태그 X)")
    @Test
    void taskTagRegisterNoTag() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Task task = new Task(1, project, "op", "제목", "내용");

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(tagRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(()->service.taskTagRegister(2, 1))
            .isInstanceOf(TagNotFoundException.class);
    }
}