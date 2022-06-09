package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @EmbeddedId
    private TagPK tagPK;

    @MapsId("taskNo")
    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Data
    @Embeddable
    public static class TagPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_no")
        private Integer tagNo;

        @Column(name = "task_no")
        private Integer taskNo;

        @Column(name = "project_no")
        private Integer projectNo;
    }

}
