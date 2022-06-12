package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.ProjectMileDTO;
import com.nhnacademy.serraytaskapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {

    @Query("select m.mileNo as mileNo, m.content as content, m.admin as admin from Milestone m" +
            " where m.project.projectNo = ?1")
    List<ProjectMileDTO> findMileListByProjectNo(Integer projectNo);
}
