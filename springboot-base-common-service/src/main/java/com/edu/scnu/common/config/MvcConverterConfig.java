package com.edu.scnu.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.edu.scnu.common.factory.BaseEnumConverterDeserializerFactory;

/**
 * @see {@link WebConfig}
 * @author Autom
 * @date 2020年2月2日
 * @version 1.0
 */
@Deprecated
public class MvcConverterConfig implements WebMvcConfigurer {
	
	@Autowired
	private BaseEnumConverterDeserializerFactory enumConverterFactory;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(enumConverterFactory);
	}

}
