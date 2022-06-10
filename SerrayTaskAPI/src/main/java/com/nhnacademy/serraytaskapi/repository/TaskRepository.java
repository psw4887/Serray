package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailTaskDTO;
import com.nhnacademy.serraytaskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t from Task t where t.project.projectNo = ?1")
    ProjectDetailTaskDTO findByProjectNo(Integer projectNo);
}
