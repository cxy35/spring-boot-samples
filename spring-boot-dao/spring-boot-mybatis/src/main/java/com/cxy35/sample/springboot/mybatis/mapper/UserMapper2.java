package com.cxy35.sample.springboot.mybatis.mapper;

import com.cxy35.sample.springboot.mybatis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 方式2：通过全注解的方式来写 SQL >> UserMapper2.java，不写 XML 文件
// @Mapper // 可在启动类中全局配置
public interface UserMapper2 {

    @Insert({"insert into t_user(username,address) values(#{username},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Integer.class)
    Integer addUser(User user);

    @Delete("delete from t_user where id=#{id}")
    Integer deleteUserById(Integer id);

    @Update("update t_user set username=#{username},address=#{address} where id=#{id}")
    Integer updateUserById(User user);

    @Select("select * from t_user")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "u"),
            @Result(property = "address", column = "a")
    })
    @Select("select username as u,address as a,id as id from t_user where id=#{id}")
    User getUserById(Long id);

    @Select("select * from t_user where username like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);
}
