package com.rochaduda.pmanager.domain.applicationservice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.entity.Member;
import com.rochaduda.pmanager.domain.entity.Project;
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
    
    private final MemberService memberService;
    private final ProjectService projectService;
    private final TaskRepository taskRepository;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData){

        Project project = getProjectIfPossible(saveTaskData.getProjectId());

        Member member = getMemberIfPossible(saveTaskData.getMemberId());
        
        Task task = Task.builder()
                        .title(saveTaskData.getTitle())
                        .description(saveTaskData.getDescription())
                        .numberOfDays(saveTaskData.getNumberOfDays())
                        .status(TaskStatus.PENDING)
                        .project(project)
                        .assignedMember(member)
                        .build();

        taskRepository.save(task);
        return task;
    }

    private Member getMemberIfPossible(String memberId) {
        Member member = null;

         if (!Objects.isNull(memberId)){
            member = memberService.loadMemberById(memberId);
         }
        return member;
    }

    private Project getProjectIfPossible(String projectId) {
        Project project = null;

         if (!Objects.isNull(projectId)){
            project = projectService.loadProject(projectId);
         }
        return project;
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
        
        Project project = getProjectIfPossible(saveTaskData.getProjectId());

        Member member = getMemberIfPossible(saveTaskData.getMemberId());
        
        Task task = loadTask(taskId);

        task.setTitle(saveTaskData.getTitle());
        task.setDescription(saveTaskData.getDescription());
        task.setStatus(convertToTaskStatus(saveTaskData.getStatus()));
        task.setNumberOfDays(saveTaskData.getNumberOfDays());
        task.setProject(project);
        task.setAssignedMember(member);

        return task;
    }

    public Page<Task> findTasks(
        String projectId,
        String memberId, 
        String statusStr,
        String partialTitle,
        Integer page,
        String direction,
        List<String> properties
    ){
        Sort sort = Sort.by(Direction.DESC, "title");

       return taskRepository.find(projectId, memberId, 
       Optional.ofNullable(statusStr).map(this::convertToTaskStatus).orElse(null)
       , partialTitle, PageRequest.of(Optional.ofNullable(page).orElse(0), 3, sort));
    }

    

    private TaskStatus convertToTaskStatus(String statusString){
        try{
            return TaskStatus.valueOf(statusString);
        }   catch(IllegalArgumentException | NullPointerException e){
            throw new InvalidTaskStatusException(statusString);
        }
    }
}
