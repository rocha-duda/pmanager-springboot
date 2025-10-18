package com.rochaduda.pmanager.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.entity.Comment;
import com.rochaduda.pmanager.domain.model.TaskStatus;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
        @Query(
        """
        SELECT c
        FROM Comment c
        WHERE
            (:taskId IS NULL OR c.project.id = :projectId) AND
            (:author IS NULL OR c.assignedMember.id = :author) AND
            (:description IS NULL OR c.description = :description) AND
            (:created_at IS NULL OR c.created_at = :created_at) AND
            (:partialTitle IS NULL OR UPPER(c.title) LIKE CONCAT('%', UPPER(:partialTitle), '%'))
                """
    )

    Page<Comment> find(
        @Param("taskId") String taskId,
        @Param("author") String author, 
        @Param("description")String description,
        @Param("created_at") LocalDateTime created_at,
        Pageable pageable
    );

}