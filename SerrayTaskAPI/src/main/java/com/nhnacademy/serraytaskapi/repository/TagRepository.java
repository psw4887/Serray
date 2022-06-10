package com.nhnacademy.serraytaskapi.repository;

import com.nhnacademy.serraytaskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
