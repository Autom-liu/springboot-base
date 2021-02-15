package com.edu.scnu.admin.web.config;

import org.springframework.context.annotation.Import;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 集成用户、机构、角色、菜单、权限一体化管理的后管系统启用注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AdminRbacConfiguraion.class})
public @interface EnableAdminRbac {

    /**
     * 是否开启远程集中身份认证调用
     * @return
     */
    boolean enableRemoteIdentify() default false;

    /**
     * 如果开启了远程集中身份调用，添加配置类
     * @return
     */
    Class<? extends RemoteIdentifyConfiguration> remoteIdentConfig() default RemoteIdentifyConfiguration.class;

    /**
     * Spring session配置类
     * @return
     */
    Class<? extends SpringHttpSessionConfiguration> sessionConfig() default SpringHttpSessionConfiguration.class;

    /**
     * Spring session id解析配置类
     * @return
     */
    Class<? extends HttpSessionIdResolver> sessionIdResolver() default CookieHttpSessionIdResolver.class;

}
