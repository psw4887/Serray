package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.TaskMileDTO;
import com.nhnacademy.serraytaskapi.entity.Task;
import com.nhnacademy.serraytaskapi.entity.TaskMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TaskMilestoneRepository extends JpaRepository<TaskMilestone, TaskMilestone.TaskMilestonePK> {

    @Query("select m.milestone.mileNo as mileNo, mile.content as content, m.startDate as start, m.endDate as end " +
            "from TaskMilestone m inner join Milestone mile on mile.mileNo = m.milestone.mileNo and m.task.taskNo = ?1")
    TaskMileDTO findTaskMile(Integer taskNo);

    Boolean existsByTask(Task task);

    @Modifying
    @Query("delete from TaskMilestone m where m.milestone.mileNo = ?1")
    void deleteByMileNo(Integer mileNo);

    @Modifying
    @Query("delete from TaskMilestone m where m.task.taskNo = ?1")
    void deleteByTaskNo(Integer taskNo);
}
