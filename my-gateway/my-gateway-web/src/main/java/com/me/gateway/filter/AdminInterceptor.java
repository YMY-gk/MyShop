package com.me.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


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
    public boolean preHandle(HttpRequest request, HttpResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");
        log.info("请求参数："+JSONUtil.toJsonStr(request));
        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    public void postHandle(HttpRequest request, HttpResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    public void afterCompletion(HttpRequest request, HttpResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }

}