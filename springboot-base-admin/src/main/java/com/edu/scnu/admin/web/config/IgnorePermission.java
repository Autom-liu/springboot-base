package com.edu.scnu.admin.web.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认条件下，所有接口都需要经过登陆验证和权限验证
 * 配置了此注解的接口，可以实际配置是否需要登陆验证和权限认证
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnorePermission {

    boolean loginCheck() default true;

    boolean permissionCheck() default true;

}
