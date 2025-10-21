package com.rochaduda.pmanager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {


}
