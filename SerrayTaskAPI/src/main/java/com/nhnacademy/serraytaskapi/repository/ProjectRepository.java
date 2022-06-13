package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.PageableProjectDTO;
import com.nhnacademy.serraytaskapi.data.dto.ProjectDetailDTO;
import com.nhnacademy.serraytaskapi.entity.Project;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Page<PageableProjectDTO> getAllBy(Pageable pageable);

    Optional<ProjectDetailDTO> findByProjectNo(Integer projectNo);

    Boolean existsProjectByAdminAndProjectNo(String id, Integer projectNo);

    @Query("select p from Project p where p.projectNo = ?1")
    Project findByProjectNoForTest(Integer projectNo);
}
