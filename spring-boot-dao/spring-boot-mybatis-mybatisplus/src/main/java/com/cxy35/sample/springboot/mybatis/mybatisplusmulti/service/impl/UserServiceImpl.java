package com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author cxy35
 * @date 2021/1/20 14:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
