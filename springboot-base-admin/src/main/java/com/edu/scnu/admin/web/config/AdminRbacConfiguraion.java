package com.edu.scnu.admin.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Configuration
public class AdminRbacConfiguraion implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        Map<String, Object> enableAdminRbac = annotationMetadata.getAnnotationAttributes("com.edu.scnu.admin.web.config.EnableAdminRbac");
        Boolean enableRemoteIdentify = (Boolean) enableAdminRbac.get("enableRemoteIdentify");
        if (enableRemoteIdentify) {
            return registerRemoteIdentifyBean(enableAdminRbac);
        } else {
            return registerLocalBean(enableAdminRbac);
        }

    }

    private String[] registerRemoteIdentifyBean(Map<String, Object> attrs) {

        Class<?> remoteConfigCls = (Class<?>) attrs.get("remoteIdentConfig");

        String[] res = new String[] {
                "com.edu.scnu.admin.web.api.RemoteUserAPI",
                "com.edu.scnu.admin.web.api.RoleAPI",
                "com.edu.scnu.admin.web.api.MenuPermissionAPI",
                remoteConfigCls.getName()
        };

        return res;
    }

    private String[] registerLocalBean(Map<String, Object> attrs) {

        Class<?> sessionConfigCls = (Class<?>) attrs.get("sessionConfig");
        Class<?> sessionIdResolverConfigCls = (Class<?>) attrs.get("sessionIdResolver");

        String[] res = new String[] {
                "com.edu.scnu.admin.web.api.LocalDeptUserAPI",
                "com.edu.scnu.admin.web.api.LocalUserAPI",
                "com.edu.scnu.admin.web.api.MenuPermissionAPI",
                sessionConfigCls.getName(),
                sessionIdResolverConfigCls.getName()
        };

        return res;
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }

}
