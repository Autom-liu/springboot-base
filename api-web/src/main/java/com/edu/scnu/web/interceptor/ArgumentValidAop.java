package com.edu.scnu.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.edu.scnu.common.exception.ParamValidException;

import lombok.extern.slf4j.Slf4j;


/**
 * 普通参数校验拦截器
 * @author Autom
 *
 */
@Order(3)	// 实现切面的优先级
@Aspect
@Component
@Slf4j
public class ArgumentValidAop {
	
//	private ParameterNameDiscoverer paramNameDiscover = new LocalVariableTableParameterNameDiscoverer();
	
	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	private final ExecutableValidator validator = factory.getValidator().forExecutables();
	
	@Pointcut("execution(public * com.edu.scnu.web.api.*.*(..))")
	public void valid() {
		
	}
	
	@Around("valid()")
	public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		
		Object target = joinPoint.getThis();
		
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		
		Set<ConstraintViolation<Object>> validateResult = validator.validateParameters(target, method, args);
		
		if(!validateResult.isEmpty()) {
//				String[] parameterNames = paramNameDiscover.getParameterNames(method);
//				for(ConstraintViolation<Object> constraintViolation : validateResult) {
//                    PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();  // 获得校验的参数路径信息
//                    int paramIndex = pathImpl.getLeafNode().getParameterIndex(); // 获得校验的参数位置
//                    String paramName = parameterNames[paramIndex];  // 获得校验的参数名称
//
//                }
			log.error("参数校验异常: ");
			throw new ParamValidException(validateResult.iterator().next().getMessage());
		}
		return joinPoint.proceed();
		
	}
	
}
