package com.zhengjian.sample.springboot.springsecurity.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhengjian.sample.springboot.springsecurity.jwt.pojo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 过滤器1：用户登录的过滤器，在用户的登录的过滤器中校验用户是否登录成功，
// 如果登录成功，则生成一个token返回给客户端，登录失败则给前端一个登录失败的提示
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        // 这里只支持 JSON 的登录方式
        // 如果想表单方式也支持，可参考spring-boot-springsecurity-loginbyjson中的MyAuthenticationFilter
        // 获取输入参数，如{"username":"user","password":"123456"}
        User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        // 进行登录校验，如果校验成功，会到successfulAuthentication的回调中，否则到unsuccessfulAuthentication的回调中
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 获取登录用户的角色
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer sb = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            sb.append(authority.getAuthority()).append(",");
        }

        // 生成token并返回
        // 数据格式：分3部分用.隔开，如eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfdXNlciwiLCJzdWIiOiJ1c2VyIiwiZXhwIjoxNTc0NzczNTkyfQ.FuPIltzXi5j14t_gSL1GoIMUZxTHKK0FvB3gds6eTZFDkQr1ZxWVxdqZ5YFbCxdkwQ_VXtPK-GgcW5Kzzx3wvw
        // 1.Header：头部（声明类型、加密算法），采用Base64Url编码，如eyJhbGciOiJIUzUxMiJ9
        // 2.Payload：载荷，就是有效数据，采用Base64Url编码，如eyJhdXRob3JpdGllcyI6IlJPTEVfdXNlciwiLCJzdWIiOiJ1c2VyIiwiZXhwIjoxNTc0NzczNTkyfQ
        // 3.Signature：签名，是整个数据的认证信息。一般根据前两步的数据，再加上服务的的密钥secret（密钥保存在服务端，不能泄露给客户端），通过Header中配置的加密算法生成。用于验证整个数据完整和可靠性。
        String jwt = Jwts.builder()
                .claim("authorities", sb)
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "abc@123")
                .compact();
        resp.setContentType("application/json;charset=utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        map.put("msg", "登录成功");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        Map<String, String> map = new HashMap<>();
        map.put("msg", "登录失败");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}
