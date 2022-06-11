package com.nhnacademy.serraytaskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "task_tag")
@NoArgsConstructor
@AllArgsConstructor
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

    @Data
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class TaskTagPK implements Serializable {

        @Column(name = "tag_no")
        private Integer tagNo;

        @Column(name = "task_no")
        private Integer taskNo;

        public TaskTagPK(Integer taskNo, Integer tagNo) {

            this.taskNo = taskNo;
            this.tagNo = tagNo;
        }
    }

}
