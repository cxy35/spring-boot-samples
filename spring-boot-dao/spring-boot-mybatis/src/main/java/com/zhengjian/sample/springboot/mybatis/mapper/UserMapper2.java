package com.zhengjian.sample.springboot.mybatis.mapper;

import com.zhengjian.sample.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author cxy35
 * @Date 2019/11/12 17:14
 */
// 方式2：通过全注解的方式来写SQL >> UserMapper2.java，不写XML文件
// @Mapper // 可在启动类中全局配置
public interface UserMapper2 {

    @Insert({"insert into user(username,address) values(#{username},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);

    @Update("update user set username=#{username},address=#{address} where id=#{id}")
    Integer updateUserById(User user);

    @Select("select * from user")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "u"),
            @Result(property = "address", column = "a")
    })
    @Select("select username as u,address as a,id as id from user where id=#{id}")
    User getUserById(Long id);

    @Select("select * from user where username like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);
}
