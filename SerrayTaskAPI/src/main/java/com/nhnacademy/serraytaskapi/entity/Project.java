package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no")
    private Integer projectNo;

    @Column(name = "project_title")
    private String title;

    @Column(name = "project_state")
    private String state;

    @Column(name = "project_admin")
    private String admin;

}
