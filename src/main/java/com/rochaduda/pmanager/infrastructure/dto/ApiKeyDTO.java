package com.rochaduda.pmanager.infrastructure.dto;

import java.time.Instant;

import com.rochaduda.pmanager.domain.document.ApiKey;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public  class ApiKeyDTO {
    
        @Id
        private final String id;

        private final String value;
        
        private final Instant expiresWhen;

        public static ApiKeyDTO create(ApiKey apiKey){
            return new ApiKeyDTO(apiKey.getId(), apiKey.getValue(), apiKey.getExpiresWhen());
        }

    
}
