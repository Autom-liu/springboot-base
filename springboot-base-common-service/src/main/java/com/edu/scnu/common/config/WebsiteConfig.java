package com.edu.scnu.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "website")
@Data
public class WebsiteConfig {
	
	private List<String> crosList;
	
}
