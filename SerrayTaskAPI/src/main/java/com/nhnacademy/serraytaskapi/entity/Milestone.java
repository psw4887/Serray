package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "milestone")
public class Milestone {

    @EmbeddedId
    private Milestone.MilestonePK milestonePK;

    @MapsId("mileTaskNo")
    @OneToOne
    @JoinColumn(name = "mile_task_no")
    private Task task;

    @Column(name = "milestone_progress")
    private String progress;

    @Column(name = "mile_start")
    private LocalDate startDate;

    @Column(name = "mile_end")
    private LocalDate endDate;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class MilestonePK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "milestone_no")
        private Integer milestoneNo;

        @Column(name = "mile_task_no")
        private Integer mileTaskNo;
    }
}
