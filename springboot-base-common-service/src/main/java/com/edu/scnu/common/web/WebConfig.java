package com.edu.scnu.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web mvc 相关配置
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private BaseEnumConverterDeserializerFactory enumConverterFactory;

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(enumConverterFactory);
	}
	
}
