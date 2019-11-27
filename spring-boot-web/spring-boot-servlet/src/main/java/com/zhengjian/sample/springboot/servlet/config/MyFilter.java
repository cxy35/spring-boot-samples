package com.zhengjian.sample.springboot.servlet.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.swing.text.AsyncBoxView;
import java.io.IOException;

/**
 * @Author zhengjian
 * @Date 2019-07-26 6:57
 */
@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
