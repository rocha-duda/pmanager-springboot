package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class ApiKeyNotFoundException extends RequestException {
    public ApiKeyNotFoundException(String apiKeyId){
        super("ApiKeyNotFound", "The API Key was not found:  " + apiKeyId);
}}
