package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailTaskDTO;
import com.nhnacademy.serraytaskapi.data.dto.TaskModifyDataDTO;
import com.nhnacademy.serraytaskapi.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select t.taskNo as taskNo, t.admin as admin, t.title as title from Task t " +
            "where t.project.projectNo = ?1")
    Page<ProjectDetailTaskDTO> findByProjectNo(Pageable pageable, Integer projectNo);

    @Query("select t from Task t where t.taskNo = ?1")
    TaskModifyDataDTO findByProjectNoForModifyData(Integer taskNo);
}
