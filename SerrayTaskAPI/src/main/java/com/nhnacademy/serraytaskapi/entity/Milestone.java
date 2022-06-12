package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "milestone")
@NoArgsConstructor
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mile_no")
    private Integer mileNo;

    @OneToOne
    @JoinColumn(name = "mile_project_no")
    private Project project;

    @Column(name = "mile_content")
    private String content;

    @Column(name = "mile_admin")
    private String admin;

    public Milestone(Project project, String content, String admin) {

        this.project = project;
        this.content = content;
        this.admin = admin;
    }
}
