package com.edu.scnu.common.cache;

import java.lang.reflect.Method;

/**
 * 用于将service对数据缓存的抽象操作
 * 此类目前仍在设计中，暂不可用。
 * 
 * 
 */
public interface ServiceCacheHandler {
	
	void beforeQueryGet(Class<?> clazz, Object target, Method method, Object[] args);
	
	void afterQuerySet(Class<?> clazz, Object target, Method method, Object[] args);
	
	void beforeAddSet(Class<?> clazz, Object target, Method method, Object[] args);
	
	void beforeUpdate(Class<?> clazz, Object target, Method method, Object[] args);
	
	void afterUpdate(Class<?> clazz, Object target, Method method, Object[] args);
	
	void beforeDelete(Class<?> clazz, Object target, Method method, Object[] args);
	
	void afterDelete(Class<?> clazz, Object target, Method method, Object[] args);
	
}
