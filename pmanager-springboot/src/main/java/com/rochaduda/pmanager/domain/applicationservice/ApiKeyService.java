package com.rochaduda.pmanager.domain.applicationservice;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.document.ApiKey;
import com.rochaduda.pmanager.domain.exception.ApiKeyExpiredException;
import com.rochaduda.pmanager.domain.exception.ApiKeyNotFoundException;
import com.rochaduda.pmanager.domain.repository.ApiKeyRepository;
import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

        private final ApiKeyRepository apiKeyRepository;
        private final AppConfigProperties props;

        public ApiKey createApiKey(){
            ApiKey apiKey = ApiKey.builder().value(UUID.randomUUID().toString()).expiresWhen(OffsetDateTime.now().plusDays(props.getSecurity().getExpirationDays()).toInstant()).build();
            apiKeyRepository.save(apiKey);
            return apiKey;
        }

        public void revokeApiKey(String id){
            ApiKey apiKey = loadApiKeyById(id);
            apiKey.setExpiresWhen(Instant.EPOCH);
            apiKeyRepository.save(apiKey);
        }

        public void validateApiKey(String apiKey){
            ApiKey apiKeyObj = loadApiKeyByValue(apiKey);

            if(apiKeyObj.isExpired(Instant.now())){
                throw new ApiKeyExpiredException(apiKeyObj.getId());
            }
        }

        private ApiKey loadApiKeyById(String id){
           return apiKeyRepository
            .findById(id)
            .orElseThrow(()-> new ApiKeyNotFoundException(id));
        }

        private ApiKey loadApiKeyByValue(String apiKey){
           return apiKeyRepository
            .findByValue(apiKey)
            .orElseThrow(()-> new ApiKeyNotFoundException(apiKey));
        }

}
