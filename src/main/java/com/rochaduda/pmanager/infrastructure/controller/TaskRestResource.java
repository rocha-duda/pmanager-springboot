package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rochaduda.pmanager.domain.applicationservice.TaskService;
import com.rochaduda.pmanager.domain.entity.Task;
import com.rochaduda.pmanager.infrastructure.dto.SaveTaskDataDTO;
import com.rochaduda.pmanager.infrastructure.dto.TaskDTO;

import jakarta.validation.Valid;

import static com.rochaduda.pmanager.infrastructure.controller.RestConstants.PATH_TASKS;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_TASKS)
@RequiredArgsConstructor
public class TaskRestResource {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid SaveTaskDataDTO saveTaskData){
       Task task = taskService.createTask(saveTaskData);

       return ResponseEntity
        .created(URI.create(PATH_TASKS + "/" + task.getId()))
        .body(TaskDTO.create(task));
    }

    
}
