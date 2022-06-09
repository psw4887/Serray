package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @EmbeddedId
    private Comment.CommentPK commentPK;

    @MapsId("commentTaskNo")
    @ManyToOne
    @JoinColumn(name = "comment_task_no")
    private Task task;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "comment_admin")
    private Member admin;

    @Column(name = "comment_content")
    private String content;



    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class CommentPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_no")
        private Integer commentNo;

        @Column(name = "comment_task_no")
        private Integer commentTaskNo;
    }
}
