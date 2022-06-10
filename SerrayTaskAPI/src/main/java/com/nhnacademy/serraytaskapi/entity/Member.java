package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "members")
@NoArgsConstructor
public class Member {

    @EmbeddedId
    private MemberPK memberPK;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    public Member(MemberPK memberPK) {

        this.memberPK = memberPK;
    }

    @Data
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class MemberPK implements Serializable {

        @Column(name = "member_id")
        private String memberId;

        @Column(name = "project_no")
        private Integer projectNo;

        public MemberPK(String id, Integer projectNo) {

            this.memberId = id;
            this.projectNo = projectNo;
        }
    }
}
