package com.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MayerFang
 * @file LoginInterceptor
 * @Description 拦截器，必须实现通过Login.jsp才能进入welcome.jsp
 * @date 2018/9/29
 */
public class SpringMVCInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI=httpServletRequest.getRequestURI();
        System.out.println("requestURI"+requestURI);
        String contextPath=httpServletRequest.getContextPath();
        System.out.println("contextPath"+contextPath);
        String url=requestURI.substring(contextPath.length());
        System.out.println("url is:"+url);
        //拦截器一般可做权限处理
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}