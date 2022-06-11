package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "project")
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no")
    private Integer projectNo;

    @Column(name = "project_admin")
    private String admin;

    @Column(name = "project_title")
    private String title;

    @Column(name = "project_content")
    private String content;

    @Column(name = "project_state")
    private String state;

    public Project(String id, String title, String content, String state) {

        this.admin = id;
        this.title = title;
        this.content = content;
        this.state = state;
    }
}
