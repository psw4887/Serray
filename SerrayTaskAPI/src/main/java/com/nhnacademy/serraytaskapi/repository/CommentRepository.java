package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Comment.CommentPK> {

}
