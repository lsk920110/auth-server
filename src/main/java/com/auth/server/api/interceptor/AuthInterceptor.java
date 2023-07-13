package com.auth.server.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    public final RequestContext createRequestContext(HttpServletRequest request, Object handler) throws Exception {

        if (null == request) {
            return null;
        }
        RequestContext context = new RequestContext();
        context.build(request, handler);
        request.setAttribute("request.context",context);
        return context;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("============ preHandle =============");
        RequestContext context = createRequestContext(request, handler);
        log.info("context : {}",context.hashCode());

        AuthParamVo authInfo = new AuthParamVo();
        authInfo.setAuthToken("auth-token-test");
        authInfo.setAdminId("adminId-test");
        authInfo.setAdminGroup(1);
        context.setAuth(authInfo);
//        context.setAuth();

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
