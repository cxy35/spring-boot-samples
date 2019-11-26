package com.zhengjian.sample.springboot.springsecurity.urp.mapper;

import com.zhengjian.sample.springboot.springsecurity.urp.pojo.Role;
import com.zhengjian.sample.springboot.springsecurity.urp.pojo.User;

import java.util.List;

public interface UserMapper {

    List<Role> getRolesById(Integer id);

    User loadUserByUsername(String username);
}
