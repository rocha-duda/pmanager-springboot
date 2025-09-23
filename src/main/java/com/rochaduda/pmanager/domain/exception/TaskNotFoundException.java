package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class TaskNotFoundException extends RequestException {
    public TaskNotFoundException(String taskId){
        super("TaskNotFound", "Task not found: " + taskId);
}}
