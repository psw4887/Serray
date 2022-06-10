package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.PageableProjectDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailDTO;
import com.nhnacademy.serraytaskapi.entity.Project;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Page<PageableProjectDTO> getAllBy(Pageable pageable);

    Optional<ProjectDetailDTO> findByProjectNo(Integer projectNo);
}
