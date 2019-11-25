package com.zhengjian.sample.springboot.springsecurity.userdb.mapper;

import com.zhengjian.sample.springboot.springsecurity.userdb.pojo.Role;
import com.zhengjian.sample.springboot.springsecurity.userdb.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer id);
}
