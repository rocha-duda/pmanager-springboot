package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {
    public ProjectNotFoundException(String projectId){
        super("ProjectNotFound", "Project not found: " + projectId);
}}
