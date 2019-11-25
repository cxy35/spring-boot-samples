package com.zhengjian.sample.springboot.springsecurity.userdb.service;

import com.zhengjian.sample.springboot.springsecurity.userdb.mapper.UserMapper;
import com.zhengjian.sample.springboot.springsecurity.userdb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 实现UserDetailsService接口
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
    }
}
