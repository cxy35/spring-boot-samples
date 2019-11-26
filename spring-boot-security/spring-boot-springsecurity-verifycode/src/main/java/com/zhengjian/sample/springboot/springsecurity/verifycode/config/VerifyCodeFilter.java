package com.zhengjian.sample.springboot.springsecurity.verifycode.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhengjian
 * @Date 2019/11/26 10:36
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {
    private String defaultFilterProcessUrl = "/doLogin";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException, AuthenticationServiceException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 验证码验证
            String requestCaptcha = request.getParameter("vercode");
            String genCaptcha = (String) request.getSession().getAttribute("s_vercode");
            if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(genCaptcha))
                throw new AuthenticationServiceException("验证码不能为空!");
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误!");
            }
        }
        chain.doFilter(request, response);
    }
}
