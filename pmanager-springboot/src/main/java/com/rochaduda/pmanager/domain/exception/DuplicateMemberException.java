package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {
    public DuplicateMemberException(String email){
        super("DuplicateMember", "A Member with the email already exists:  " + email);
}}
