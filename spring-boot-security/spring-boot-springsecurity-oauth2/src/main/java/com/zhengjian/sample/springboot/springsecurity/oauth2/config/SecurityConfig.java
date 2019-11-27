package com.zhengjian.sample.springboot.springsecurity.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

// 方法2：通过SecurityConfig配置用户名/密码
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中配置2个用户
        /*auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("admin")
                .and()
                .withUser("user").password("123456").roles("user");// 密码不加密*/

        // 明文123
        auth.inMemoryAuthentication()
                .withUser("admin").password("$2a$10$fB2UU8iJmXsjpdk6T6hGMup8uNcJnOGwo2.QGR.e3qjIsdPYaS4LO").roles("admin")
                .and()
                .withUser("user").password("$2a$10$3TQ2HO/Xz1bVHw5nlfYTBON2TDJsQ0FMDwAS81uh7D.i9ax5DR46q").roles("user");// 密码加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1.先访问 [POST]http://127.0.0.1:8080/oauth/token 获取token
        // 输入参数：
        /*client_id:cid
        client_secret:123
        grant_type:password
        username:user
        password:123456
        scope:all（这个貌似可以不用传）*/
        // 输出参数：
        /*{
            "access_token": "3ef17e82-dd2e-4aef-a96f-8c964b4be47c",
                "token_type": "bearer",
                "refresh_token": "f555ed3d-9348-4d35-a6fe-1e2a35079d11",
                "expires_in": 1799,
                "scope": "all"
        }*/

        // 2.再根据1中获取的access_token访问实际的请求 [GET]http://127.0.0.1:8080/user/hello?access_token=3ef17e82-dd2e-4aef-a96f-8c964b4be47c

        // 另外可访问 [POST]http://127.0.0.1:8080/oauth/token 刷新token
        // 输入参数：
        /*client_id:cid
        client_secret:123
        grant_type:refresh_token
        refresh_token:f555ed3d-9348-4d35-a6fe-1e2a35079d11*/
        // 输出参数：
        /*{
            "access_token": "04465dc8-f9b5-4591-bbef-dd4df442b7c3",
                "token_type": "bearer",
                "refresh_token": "f555ed3d-9348-4d35-a6fe-1e2a35079d11",
                "expires_in": 1799,
                "scope": "all"
        }*/

        // 放过 /oauth/token 等请求
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .csrf().disable();
    }
}
