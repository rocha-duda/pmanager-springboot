package com.rochaduda.pmanager.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "app")
@Data
public class AppConfigProperties {


    private final int pageSize;
    
}
