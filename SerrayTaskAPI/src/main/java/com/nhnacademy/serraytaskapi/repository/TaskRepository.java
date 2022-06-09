package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
