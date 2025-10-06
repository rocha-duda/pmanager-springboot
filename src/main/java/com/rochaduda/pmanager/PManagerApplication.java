package com.rochaduda.pmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;

@SpringBootApplication(
	exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class}
)
@EnableConfigurationProperties(AppConfigProperties.class)
public class PManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PManagerApplication.class, args);
	}

}
