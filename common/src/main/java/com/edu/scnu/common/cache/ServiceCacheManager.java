package com.edu.scnu.common.cache;

import java.lang.reflect.Method;

/**
 * 用于将service对数据缓存的抽象操作
 * 此类目前仍在设计中，暂不可用。
 * 
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于将service对数据缓存的抽象操作
 * 此类目前仍在设计中，暂不可用。
 * 
 * 
 */
@Component
public class ServiceCacheManager {

	@Autowired
	private ServiceCacheHandler cacheHandler;
	
	public void beforeQueryGet(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.beforeQueryGet(clazz, target, method, args);
	}
	
	public void afterQuerySet(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.afterQuerySet(clazz, target, method, args);
	}
	
	public void beforeAddSet(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.beforeAddSet(clazz, target, method, args);
	}
	
	public void beforeUpdate(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.beforeUpdate(clazz, target, method, args);
	}
	
	public void afterUpdate(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.afterUpdate(clazz, target, method, args);
	}
	
	public void beforeDelete(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.beforeDelete(clazz, target, method, args);
	}
	
	public void afterDelete(Class<?> clazz, Object target, Method method, Object[] args) {
		cacheHandler.afterDelete(clazz, target, method, args);
	}
	
}
