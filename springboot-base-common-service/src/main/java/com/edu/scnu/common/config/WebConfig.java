package com.edu.scnu.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.OrderComparator;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.edu.scnu.common.factory.BaseEnumConverterDeserializerFactory;
import com.edu.scnu.common.web.CommonRequestBodyResolver;
import com.edu.scnu.common.web.RequestPemissionInteceptor;

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
	private CommonRequestBodyResolver commonRequestBodyResolver;
	
	@Autowired
	private BaseEnumConverterDeserializerFactory enumConverterFactory;
	
	@Autowired
	private RequestPemissionInteceptor requestLogInteceptor;
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(commonRequestBodyResolver);
		OrderComparator.sort(resolvers);
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(enumConverterFactory);
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLogInteceptor).addPathPatterns("/**");
	}
	
	
	
}
