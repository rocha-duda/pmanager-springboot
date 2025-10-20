package com.rochaduda.pmanager.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
       

    Page<Comment> find(
        @Param("taskId") String taskId,
        @Param("author") String author, 
        @Param("description")String description,
        @Param("created_at") LocalDateTime created_at,
        Pageable pageable
    );

}