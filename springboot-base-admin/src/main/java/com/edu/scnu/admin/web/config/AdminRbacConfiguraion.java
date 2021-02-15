package com.edu.scnu.admin.web.config;

import com.edu.scnu.admin.web.api.LocalDeptUserAPI;
import com.edu.scnu.admin.web.api.RemoteUserAPI;
import com.edu.scnu.admin.web.api.RoleMenuPermissionAPI;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.MethodParameter;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Configuration
public class AdminRbacConfiguraion extends WebMvcConfigurationSupport implements ImportSelector {


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

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserArgumentResolve());
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestPemissionInteceptor()).addPathPatterns("/**");
    }

    private String[] registerRemoteIdentifyBean(Map<String, Object> attrs) {

        Class<?> remoteConfigCls = (Class<?>) attrs.get("remoteIdentConfig");

        String[] res = new String[] {
                RemoteUserAPI.class.getName(),
                RoleMenuPermissionAPI.class.getName(),
                remoteConfigCls.getName()
        };

        return res;
    }

    private String[] registerLocalBean(Map<String, Object> attrs) {

        Class<?> sessionConfigCls = (Class<?>) attrs.get("sessionConfig");
        Class<?> sessionIdResolverConfigCls = (Class<?>) attrs.get("sessionIdResolver");

        String[] res = new String[] {
                LocalDeptUserAPI.class.getName(),
                RoleMenuPermissionAPI.class.getName(),
                sessionConfigCls.getName(),
                sessionIdResolverConfigCls.getName()
        };

        return res;
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }


    private static class UserArgumentResolve implements HandlerMethodArgumentResolver {

        @Override
        public boolean supportsParameter(MethodParameter parameter) {
            return parameter.hasParameterAnnotation(CurrentUser.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
            HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
            HttpSession session = request.getSession();
            return session.getAttribute("CURRENT_USER");
        }
    }

}
