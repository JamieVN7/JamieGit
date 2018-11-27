package com.example.login.web.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
public class LoginFilter extends HandlerInterceptorAdapter {
    //会话key等常量
    public static final String SESSION_KEY = "loginUser";
    private static final String REDIRECT_URL = "/login/index/";


    //前置拦截器（执行请求之前）
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //获取session
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_KEY) != null){
            return true;
        }
        response.sendRedirect(REDIRECT_URL);
        return false;
    }
}
