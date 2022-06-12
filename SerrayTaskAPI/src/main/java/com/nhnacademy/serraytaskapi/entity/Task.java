package com.nhnacademy.serraytaskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_no")
    private Integer taskNo;

    @ManyToOne
    @JoinColumn(name = "task_project_no")
    private Project project;

    @Column(name = "task_admin")
    private String admin;

    @Column(name = "task_title")
    private String title;

    @Column(name = "task_content")
    private String content;

    public Task(Project project, String id, String title, String content) {

        this.project = project;
        this.admin = id;
        this.title = title;
        this.content = content;
    }
}
