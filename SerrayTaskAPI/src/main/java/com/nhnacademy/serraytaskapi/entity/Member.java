package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "members")
public class Member {

    @EmbeddedId
    private MemberPK memberPK;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "member_id")
    private String memberId;

    @Data
    @Embeddable
    @EqualsAndHashCode
    public static class MemberPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_no")
        private Integer memberNo;

        @Column(name = "project_no")
        private Integer projectNo;
    }
}
