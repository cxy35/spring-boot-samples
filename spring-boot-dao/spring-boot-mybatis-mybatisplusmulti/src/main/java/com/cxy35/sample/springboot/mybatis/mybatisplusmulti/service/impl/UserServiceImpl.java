package com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxy35
 * @date 2021/1/20 14:35
 */
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 重写父类的方法或自定义方法，指定数据源，会覆盖类上的数据源
     *
     * @return
     */
    @DS("slave")
    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }
}
