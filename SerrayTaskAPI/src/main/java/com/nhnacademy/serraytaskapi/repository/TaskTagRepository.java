package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.TaskTagPK> {

}
