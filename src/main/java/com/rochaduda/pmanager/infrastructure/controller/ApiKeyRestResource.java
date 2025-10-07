package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.rochaduda.pmanager.domain.applicationservice.ApiKeyService;

import com.rochaduda.pmanager.domain.document.ApiKey;

import com.rochaduda.pmanager.infrastructure.dto.ApiKeyDTO;




import static com.rochaduda.pmanager.infrastructure.controller.RestConstants.PATH_API_KEYS;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_API_KEYS)
@RequiredArgsConstructor
public class ApiKeyRestResource {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyDTO> createApiKey(){
       ApiKey apiKey = apiKeyService.createApiKey();

       return ResponseEntity
        .created(URI.create( PATH_API_KEYS + "/" + apiKey.getId()))
        .body(ApiKeyDTO.create(apiKey));
    }

    @PutMapping("{id}/revoke")
    public ResponseEntity<Void> revokeApiKey(@PathVariable("id") String id){
        apiKeyService.revokeApiKey(id);

        return ResponseEntity.noContent().build();
    }

    
}
