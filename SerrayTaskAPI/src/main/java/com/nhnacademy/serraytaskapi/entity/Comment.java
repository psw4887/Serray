package com.nhnacademy.serraytaskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment {

    @EmbeddedId
    private Comment.CommentPK commentPK;

    @MapsId("commentTaskNo")
    @ManyToOne
    @JoinColumn(name = "comment_task_no")
    private Task task;

    @Column(name = "comment_admin")
    private String admin;

    @Column(name = "comment_content")
    private String content;

    public Comment(CommentPK commentPK, Task task, String admin, String content) {

        this.commentPK = commentPK;
        this.task = task;
        this.admin = admin;
        this.content = content;
    }

    @Data
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_no")
        private Integer commentNo;

        @Column(name = "comment_task_no")
        private Integer commentTaskNo;

        public CommentPK(Integer taskNo) {

            this.commentTaskNo = taskNo;
        }
    }
}
