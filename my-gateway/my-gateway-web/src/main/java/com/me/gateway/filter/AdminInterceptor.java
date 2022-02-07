package com.me.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author guokui
 * @class MyShop
 * @date 2022/1/25 16:45
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("1---------请求参数："+JSONUtil.toJsonStr(request));
        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("2---------执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("3-------------执行了TestInterceptor的afterCompletion方法");

    }

}