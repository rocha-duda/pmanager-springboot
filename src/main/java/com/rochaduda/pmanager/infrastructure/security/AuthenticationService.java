package com.rochaduda.pmanager.infrastructure.security;

import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.applicationservice.ApiKeyService;
import com.rochaduda.pmanager.domain.exception.ApiKeyExpiredException;
import com.rochaduda.pmanager.domain.exception.ApiKeyNotFoundException;
import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ApiKeyService apiKeyService;

    private final  AppConfigProperties props;

    private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

    public Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if(!Objects.equals(apiKey, props.getSecurity().getMasterApiKey())){
            try{
            apiKeyService.validateApiKey(apiKey);
            }catch(ApiKeyNotFoundException | ApiKeyExpiredException e){
                throw new BadCredentialsException("API key is not valid: "+ apiKey);
            }
        }

        return new ApiKeyAuthenticationToken(apiKey);
    }

}
