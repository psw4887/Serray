package com.nhnacademy.serraytaskapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "member_id")
    private String memberId;
}
