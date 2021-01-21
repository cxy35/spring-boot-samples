package com.cxy35.sample.springboot.mybatis.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy35.sample.springboot.mybatis.mybatisplus.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.mybatisplus.pojo.User;
import com.cxy35.sample.springboot.mybatis.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author cxy35
 * @date 2021/1/20 14:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
