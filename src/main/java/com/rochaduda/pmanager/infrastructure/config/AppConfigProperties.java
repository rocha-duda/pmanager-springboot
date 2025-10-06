package com.rochaduda.pmanager.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app")
@Data
public class AppConfigProperties {


    private final int pageSize;

    private final General general;
    private final Security security;


    @Data
    public static class General{
        private final int pageSize;
    }
    
    @Data
    public static class Security{
        private final String apiKey;
    }
}
