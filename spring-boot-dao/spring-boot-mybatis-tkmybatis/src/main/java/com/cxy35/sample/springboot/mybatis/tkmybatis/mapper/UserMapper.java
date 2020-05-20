package com.cxy35.sample.springboot.mybatis.tkmybatis.mapper;

import com.cxy35.sample.springboot.mybatis.tkmybatis.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    // 测试用，非必须
    List<User> selectByRoleId(Integer roleId);
}
