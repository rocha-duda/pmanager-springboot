package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<List<TaskDTO>>findTasks(
        @RequestParam(value = "projectId", required = false) String projectId,
        @RequestParam(value = "memberId", required = false) String memberId, 
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "partialTitle", required = false) String partialTitle,
        @RequestParam(value = "page", required = false) Integer page
    ) {
       Page<Task> tasks =  taskService.findTasks(projectId, memberId, status, partialTitle, page);
        return ResponseEntity.ok(tasks.stream().map(TaskDTO::create).toList());
    }
}
