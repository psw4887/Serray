package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "task_tag")
public class TaskTag {

    @EmbeddedId
    private TaskTagPK taskTagPK;

    @MapsId("tagNo")
    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;

    @MapsId("taskNo")
    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @Column(name = "task_tag_admin")
    private String admin;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class TaskTagPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_no")
        private Integer tagNo;

        @Column(name = "task_no")
        private Integer taskNo;
    }

}
