package com.edu.scnu.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "resource")
@Data
public class ResourceConfig {
	
	private String savePath;
	
	private String visitPath;
	
}
