package com.nhnacademy.serraytaskapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tag")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private Integer tagNo;

    @ManyToOne
    @JoinColumn(name = "tag_project_no")
    private Project project;

    @Column(name = "tag_content")
    private String content;

    @Column(name = "tag_admin")
    private String admin;

    public Tag(Project project, String content, String admin) {

        this.project = project;
        this.content = content;
        this.admin = admin;
    }
}
