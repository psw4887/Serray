package com.nhnacademy.serraytaskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "task")
public class Task {

    @EmbeddedId
    private TaskPK taskPK;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_content")
    private String content;

    @Column(name = "task_admin")
    private String admin;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class TaskPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "task_no")
        private Integer taskNo;

        @Column(name = "project_no")
        private Integer projectNo;
    }
}
