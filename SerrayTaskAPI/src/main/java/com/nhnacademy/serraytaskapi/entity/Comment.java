package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @EmbeddedId
    private Comment.CommentPK commentPK;

    @MapsId("taskNo")
    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_admin")
    private String admin;

    @Data
    @Embeddable
    public static class CommentPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_no")
        private Integer commentNo;

        @Column(name = "task_no")
        private Integer taskNo;

        @Column(name = "project_no")
        private Integer projectNo;
    }
}
