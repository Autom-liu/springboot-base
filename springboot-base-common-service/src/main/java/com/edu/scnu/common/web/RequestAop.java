package com.edu.scnu.common.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 请求日志记录 AOP切面
 * @author Administrator
 *
 */
@Aspect
@Component
public class RequestAop {
	
	Logger logger = LoggerFactory.getLogger(RequestAop.class);

	@Pointcut("execution(public * com.edu.scnu.*.web.api.*.*(..))")
	public void logApi() {
		
	}
	
	@Before("logApi()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		logger.info("url={}, method={}, param={}", request.getRequestURL(), request.getMethod(), joinPoint.getArgs());

	}
	
	@AfterReturning(returning="object", pointcut="logApi()")
	public void doAfterReturning(Object object) {
		logger.info("response={}", object);
	}
	
}
