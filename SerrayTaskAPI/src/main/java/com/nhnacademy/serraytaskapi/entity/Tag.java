package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tag")
public class Tag {

    @EmbeddedId
    private TagPK tagPK;

    @MapsId("tagTaskNo")
    @ManyToOne
    @JoinColumn(name = "tag_task_no")
    private Task task;

    @Column(name = "tag_admin")
    private String admin;

    @Column(name = "tag_content")
    private String content;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class TagPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tag_no")
        private Integer tagNo;

        @Column(name = "tag_task_no")
        private Integer tagTaskNo;
    }

}
