package com.edu.scnu.common.web;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.edu.scnu.common.web.annotation.PermissionRequire;

/**
 * 请求权限校验拦截器，使用者应根据自己实际需求完成校验方法
 * @author Autom
 * @date 2020年2月7日
 * @version 1.0
 *
 */
@Component
public class RequestPemissionInteceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestPemissionInteceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("token");
		HandlerMethod mh = (HandlerMethod) handler;
		Method method = mh.getMethod();
		logger.info("token: {}", token);
		PermissionRequire permissionRequire = method.getAnnotation(PermissionRequire.class);
		if(permissionRequire != null && StringUtils.isEmpty(token)) {
			// we recommend throw a BizException to return
			return false;
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
