package com.edu.scnu.common.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 *	代理扩展Criteria的功能
 * @author Autom
 * @see user模块的 com.edu.scnu.user.service.impl.UserServiceImpl.getUserAlive(UserQuery)
 */
public class CriteriaProxy implements MethodInterceptor {
	
	private Object target;
	
	private Object proxyTarget;
	
	private static CriteriaProxy instance = new CriteriaProxy();
	
	private CriteriaProxy() {
		 
	}
	
	public static Object getInstance(Object target) {
		return instance.getInstanceProxy(target);
	}
	
	private Object getInstanceProxy(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(target.getClass());
		
		enhancer.setCallback(this);
		
		proxyTarget = enhancer.create();
		
		return proxyTarget;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		for (Object arg : args) {
			if(arg == null) {
				return proxyTarget;
			}
		}
		method.invoke(target, args);
		return proxyTarget;
	}

}
