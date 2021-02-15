package com.edu.scnu.admin.web.config;

import com.edu.scnu.admin.enums.ErrorEnum;
import com.edu.scnu.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
public class RequestPemissionInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.warn("handler instanceof HandlerMethod false : {}", handler);
            throw new BizException(ErrorEnum.ERRCODE_0008);
        }
        HandlerMethod method = (HandlerMethod) handler;
        IgnorePermission ignorePermission = method.getMethodAnnotation(IgnorePermission.class);
        boolean loginCheck = ignorePermission == null || ignorePermission.loginCheck();
        boolean permissionCheck = ignorePermission == null || ignorePermission.permissionCheck();
        HttpSession session = request.getSession(false);
        if (session == null && loginCheck) {
            throw new BizException(ErrorEnum.ERRCODE_0008);
        }
        if (session == null) {
            return true;
        }
        Object currentUser = session.getAttribute("CURRENT_USER");
        if (currentUser == null && loginCheck) {
            throw new BizException(ErrorEnum.ERRCODE_0008);
        }
        List<String> currentPermission = (List<String>) session.getAttribute("CURRENT_PERMISSION");
        String uri = request.getRequestURI();

        if (CollectionUtils.isEmpty(currentPermission) && permissionCheck) {
            throw new BizException(ErrorEnum.ERRCODE_0012);
        }
        if (CollectionUtils.isEmpty(currentPermission)) {
            return true;
        }
        if (!CollectionUtils.contains(currentPermission.iterator(), uri) && permissionCheck) {
            throw new BizException(ErrorEnum.ERRCODE_0012);
        }

        return true;
    }
}
