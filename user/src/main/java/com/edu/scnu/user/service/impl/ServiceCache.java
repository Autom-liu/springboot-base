package com.edu.scnu.user.service.impl;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import com.edu.scnu.common.cache.ServiceCacheHandler;

@Component
public class ServiceCache implements ServiceCacheHandler {

	@Override
	public void beforeQueryGet(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterQuerySet(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeAddSet(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeUpdate(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterUpdate(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDelete(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterDelete(Class<?> clazz, Object target, Method method, Object[] args) {
		// TODO Auto-generated method stub
		
	}

}
