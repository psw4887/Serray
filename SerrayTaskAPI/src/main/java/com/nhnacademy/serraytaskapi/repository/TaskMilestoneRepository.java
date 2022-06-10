package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.TaskMilestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskMilestoneRepository extends JpaRepository<TaskMilestone, TaskMilestone.TaskMilestonePK> {

}
