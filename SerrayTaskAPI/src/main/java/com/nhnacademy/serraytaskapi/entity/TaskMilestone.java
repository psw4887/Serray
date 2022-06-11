package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "task_milestone")
public class TaskMilestone {

    @EmbeddedId
    private TaskMilestone.TaskMilestonePK taskMilestonePK;

    @MapsId("mileNo")
    @ManyToOne
    @JoinColumn(name = "mile_no")
    private Milestone milestone;

    @MapsId("taskNo")
    @OneToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @Column(name = "mile_start")
    private LocalDate startDate;

    @Column(name = "mile_end")
    private LocalDate endDate;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class TaskMilestonePK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "mile_no")
        private Integer mileNo;

        @Column(name = "task_no")
        private Integer taskNo;
    }
}
