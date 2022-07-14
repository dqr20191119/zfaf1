package com.cesgroup.prison.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *@ClassName XssFilter
 *@Description TODO
 *@Author lh
 *@Date 2020/9/2 10:10
 *
 **/
//@WebFilter(urlPatterns = "/*")
public class XssFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XssHttpServletRequestWraper((HttpServletRequest) servletRequest),response);
    }

    @Override
    public void destroy() {

    }
}
