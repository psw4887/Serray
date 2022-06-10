package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {

}
