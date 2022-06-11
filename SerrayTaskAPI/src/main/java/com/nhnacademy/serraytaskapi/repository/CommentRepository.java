package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.CommentDataDTO;
import com.nhnacademy.serraytaskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Comment.CommentPK> {

    @Query("select c.commentPK.commentNo as commentNo, c.admin as admin, c.content as content" +
            " from Comment c where c.task.taskNo = ?1")
    List<CommentDataDTO> getCommentListByTaskNo(Integer taskNo);

    @Query("select c.admin from Comment c where c.commentPK.commentNo = ?1")
    String findByCommentNo(Integer commentNo);

    @Modifying
    @Query("delete from Comment c where c.commentPK.commentNo = ?1")
    void deleteComment(Integer commentNo);

    @Query("select c from Comment c where c.commentPK.commentNo = ?1")
    Optional<Comment> selectCommentByCommentNo(Integer commentNo);
}
