package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> loadTask(@PathVariable("id") String taskId){
        
        Task task = taskService.loadTask(taskId);
        return ResponseEntity.ok(
            TaskDTO.create(task)
        );

        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String taskId){

    taskService.deleteTask(taskId);
    return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") String taskId, @RequestBody @Valid SaveTaskDataDTO saveTaskData){

        Task task = taskService.updateTask(taskId, saveTaskData);
        return ResponseEntity.ok(TaskDTO.create(task));
    }
}
