package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.data.dto.TaskTagDTO;
import com.nhnacademy.serraytaskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.TaskTagPK> {

    @Query("select t.tag.tagNo as tagNo, tag.content as content from TaskTag t inner join Tag tag " +
            "on t.tag.tagNo = tag.tagNo and t.task.taskNo = ?1")
    List<TaskTagDTO> findTaskTagList(Integer taskNo);

    @Modifying
    @Query("delete from TaskTag t where t.tag.tagNo = ?1")
    void deleteByTagNo(Integer tagNo);

    @Modifying
    @Query("delete from TaskTag t where t.task.taskNo = ?1")
    void deleteByTaskNo(Integer taskNo);
}
