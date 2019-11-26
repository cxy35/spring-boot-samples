package com.zhengjian.sample.springboot.springsecurity.loginbyjson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhengjian.sample.springboot.springsecurity.loginbyjson.filter.MyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// 方法2：通过SecurityConfig配置用户名/密码
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();// 密码不加密
        return new BCryptPasswordEncoder();// 密码加密
    }

    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                Map<String, Object> map = new HashMap<>();
                map.put("status", 200);
                map.put("msg", authentication.getPrincipal());
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                Map<String, Object> map = new HashMap<>();
                map.put("status", 401);
                if (e instanceof LockedException) {
                    map.put("msg", "账户被锁定，登录失败!");
                } else if (e instanceof BadCredentialsException) {
                    map.put("msg", "用户名或密码输入错误，登录失败!");
                } else if (e instanceof DisabledException) {
                    map.put("msg", "账户被禁用，登录失败!");
                } else if (e instanceof AccountExpiredException) {
                    map.put("msg", "账户过期，登录失败!");
                } else if (e instanceof CredentialsExpiredException) {
                    map.put("msg", "密码过期，登录失败!");
                } else {
                    map.put("msg", "登录失败!");
                }
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中配置2个用户
        /*auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("admin")
                .and()
                .withUser("user").password("123456").roles("user");// 密码不加密*/

        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$fB2UU8iJmXsjpdk6T6hGMup8uNcJnOGwo2.QGR.e3qjIsdPYaS4LO").roles("admin")
                .and()
                .withUser("user").password("$2a$10$3TQ2HO/Xz1bVHw5nlfYTBON2TDJsQ0FMDwAS81uh7D.i9ax5DR46q").roles("user");// 密码加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
