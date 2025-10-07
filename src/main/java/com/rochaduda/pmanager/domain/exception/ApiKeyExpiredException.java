package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class ApiKeyExpiredException extends RequestException {
    public ApiKeyExpiredException(String apiKeyId){
        super("ApiKeyExpired", "The API Key expired:  " + apiKeyId);
}}
