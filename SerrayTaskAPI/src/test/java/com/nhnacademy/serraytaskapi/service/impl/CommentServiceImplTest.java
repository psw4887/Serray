package com.nhnacademy.serraytaskapi.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.serraytaskapi.data.vo.CommentRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Comment;
import com.nhnacademy.serraytaskapi.entity.Project;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.repository.CommentRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.CommentService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CommentServiceImplTest {

    @Autowired
    CommentService service;

    @MockBean
    CommentRepository commentRepository;
    @MockBean
    TaskRepository taskRepository;

    @Test
    void getCommenter() {

        when(commentRepository.findByCommentNo(anyInt())).thenReturn("op");

        assertThat(service.getCommenter(1)).isEqualTo("op");
    }

    @Test
    void registerComment() {

        Project project = new Project(1, "op", "제목", "내용", "활성");
        Task task = new Task(1, project, "op", "제목", "내용");

        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(task));

        service.registerComment(new CommentRegisterVO(1, "op", "content"));

        verify(commentRepository, atLeastOnce()).save(any());
    }

    @Test
    void modifyComment() {
    }

    @Test
    void deleteComment() {
    }
}