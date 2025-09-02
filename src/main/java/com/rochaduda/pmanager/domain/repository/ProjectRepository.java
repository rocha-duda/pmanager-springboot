package com.rochaduda.pmanager.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.entity.Project;

@Repository //dar conhecimento ao SB que essa classe é auto-gerenciada por ele
public interface ProjectRepository extends JpaRepository<Project, String> {

    Optional<Project> findByName(String name);
}
