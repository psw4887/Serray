package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.ProjectTagDTO;
import com.nhnacademy.serraytaskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t.tagNo as tagNo, t.content as content, t.admin as admin from Tag t" +
            " where t.project.projectNo = ?1")
    List<ProjectTagDTO> findTagListByProjectNo(Integer projectNo);
}
