package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "milestone")
public class Milestone {

    @EmbeddedId
    private Milestone.MilestonePK milestonePK;

    @MapsId("taskNo")
    @OneToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "milestone_progress")
    private String progress;

    @Data
    @Embeddable
    public static class MilestonePK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "milestone_no")
        private Integer milestoneNo;

        @Column(name = "task_no")
        private Integer taskNo;

        @Column(name = "project_no")
        private Integer projectNo;
    }
}
