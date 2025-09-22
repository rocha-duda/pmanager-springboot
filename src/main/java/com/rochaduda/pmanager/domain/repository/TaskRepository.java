package com.rochaduda.pmanager.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
