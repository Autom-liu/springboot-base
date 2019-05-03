package com.edu.scnu.common.proxy;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.scnu.common.cache.ServiceCacheManager;

/**
 * 	此类功能仍在设计中，暂不可用！！
 * @author Administrator
 *
 */
//@Component
//@Aspect
public class ServiceCacheProxy {
	
	@Autowired
	private ServiceCacheManager cacheManager;
	
	@Pointcut("execution(public * com.edu.scnu.*.service.*.*(..))")
	public void caching() {
		
	}
	
	@Around("caching()")
	public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("service被代理了....");
		
		Class<?> clazz = joinPoint.getSignature().getClass();
		
		Object[] args = joinPoint.getArgs();
		
		Object target = joinPoint.getThis();
		
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

		cacheManager.beforeQueryGet(clazz, target, method, args);
		
		return joinPoint.proceed();
		
	}
}
