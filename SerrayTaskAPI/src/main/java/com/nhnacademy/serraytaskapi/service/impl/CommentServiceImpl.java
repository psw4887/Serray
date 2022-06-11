package com.nhnacademy.serraytaskapi.service.impl;

import com.nhnacademy.serraytaskapi.data.vo.CommentModifyVO;
import com.nhnacademy.serraytaskapi.data.vo.CommentRegisterVO;
import com.nhnacademy.serraytaskapi.entity.Comment;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.exception.CommentNotFoundException;
import com.nhnacademy.serraytaskapi.exception.TaskNotFoundException;
import com.nhnacademy.serraytaskapi.repository.CommentRepository;
import com.nhnacademy.serraytaskapi.repository.TaskRepository;
import com.nhnacademy.serraytaskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository cRepository;
    private final TaskRepository tRepository;

    @Override
    public String getCommenter(Integer commentNo) {

        return cRepository.findByCommentNo(commentNo);
    }

    @Transactional
    @Override
    public void registerComment(CommentRegisterVO vo) {

        Comment.CommentPK commentPK = new Comment.CommentPK(vo.getTaskNo());
        Task task = tRepository.findById(vo.getTaskNo()).orElseThrow(TaskNotFoundException::new);
        Comment comment = new Comment(commentPK, task, vo.getAdmin(), vo.getContent());

        cRepository.save(comment);
    }

    @Transactional
    @Override
    public void modifyComment(CommentModifyVO vo) {

        Comment comment = cRepository.selectCommentByCommentNo(vo.getCommentNo()).orElseThrow(CommentNotFoundException::new);

        comment.setContent(vo.getContent());

        cRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Integer commentNo) {

        cRepository.deleteComment(commentNo);
    }
}
