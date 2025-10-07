package com.rochaduda.pmanager.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rochaduda.pmanager.domain.document.ApiKey;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {
    
    Optional<ApiKey> findByValue(String value);
    
}
