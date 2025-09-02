package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class DuplicateProjectException extends RequestException {
    public DuplicateProjectException(String name){
        super("DuplicateProject", "A project with the name already exists:  " + name);
}}
