package com.rochaduda.pmanager.domain.applicationservice;

import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.entity.Task;
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
}
