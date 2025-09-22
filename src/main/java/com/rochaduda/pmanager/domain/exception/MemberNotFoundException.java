package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {
    public MemberNotFoundException(String memberId){
        super("MemberNotFound", "Member not found: " + memberId);
}}
