package com.edu.scnu.admin.web.config;

import com.edu.scnu.admin.enums.ErrorEnum;
import com.edu.scnu.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        if (ignorePermission != null) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new BizException(ErrorEnum.ERRCODE_0008);
        }
        Object currentUser = session.getAttribute("CURRENT_USER");
        if (currentUser == null) {
            throw new BizException(ErrorEnum.ERRCODE_0008);
        }
        return true;
    }
}
