package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class InvalidProjectStatusException extends RequestException {

    public InvalidProjectStatusException(String statusStr){
        super("InvalidProjectStatus", "Invalid Project Status: " + statusStr);
    }
}
