package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class InvalidTaskStatusException extends RequestException {

    public InvalidTaskStatusException(String statusStr){
        super("nvalidTaskStatus", "Invalid Task Status: " + statusStr);
    }
}
