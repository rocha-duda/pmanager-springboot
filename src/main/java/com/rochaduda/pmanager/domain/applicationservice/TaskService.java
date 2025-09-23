package com.rochaduda.pmanager.domain.applicationservice;

import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.entity.Task;
import com.rochaduda.pmanager.domain.exception.InvalidTaskStatusException;
import com.rochaduda.pmanager.domain.exception.TaskNotFoundException;
import com.rochaduda.pmanager.domain.model.TaskStatus;
import com.rochaduda.pmanager.domain.repository.TaskRepository;
import com.rochaduda.pmanager.infrastructure.dto.SaveTaskDataDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData){
        
        Task task = Task.builder()
                        .title(saveTaskData.getTitle())
                        .description(saveTaskData.getDescription())
                        .numberOfDays(saveTaskData.getNumberOfDays())
                        .status(TaskStatus.PENDING)
                        .build();

        taskRepository.save(task);
        return task;
    }

    public Task loadTask(String taskId ){
    
        return taskRepository
        .findById(taskId)
        .orElseThrow(()-> new TaskNotFoundException(taskId));
    }

    @Transactional
    public void deleteTask(String taskId){
        
        taskRepository
        .deleteById(taskId);
        
        
    }

    @Transactional
    public Task updateTask(String taskId, SaveTaskDataDTO saveTaskData){
        
        Task task = loadTask(taskId);

        task.setTitle(saveTaskData.getTitle());
        task.setDescription(saveTaskData.getDescription());
        task.setStatus(convertToTaskStatus(saveTaskData.getStatus()));
        task.setNumberOfDays(saveTaskData.getNumberOfDays());

        return task;
    }

    private TaskStatus convertToTaskStatus(String statusString){
        try{
            return TaskStatus.valueOf(statusString);
        }   catch(IllegalArgumentException | NullPointerException e){
            throw new InvalidTaskStatusException(statusString);
        }
    }
}
