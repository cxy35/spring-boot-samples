package com.zhengjian.sample.springboot.jdbctemplatemulti.service1;

import com.zhengjian.sample.springboot.jdbctemplatemulti.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

@Service
public class UserService1 {
    // 因为同类型的有多个，所以需要指定名称
    // 注入方法1
    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;

    // 注入方法2
    // @Resource(name = "jdbcTemplateOne")
    // JdbcTemplate jdbcTemplateOne;

    public int addUser(User user) {
        return jdbcTemplateOne.update("insert into user (username,address) values (?,?);", user.getUsername(), user.getAddress());
    }

    public int addUser2(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplateOne.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into user (username,address) values (?,?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return update;
    }

    public int deleteUserById(Integer id) {
        return jdbcTemplateOne.update("delete from user where id=?", id);
    }

    public int updateUserById(User user) {
        return jdbcTemplateOne.update("update user set username=?,address=? where id=?", user.getUsername(), user.getAddress(), user.getId());
    }

    public List<User> getAllUsers() {
        return jdbcTemplateOne.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                user.setId(id);
                user.setUsername(username);
                user.setAddress(address);
                return user;
            }
        });
    }

    public List<User> getAllUsers2() {
        return jdbcTemplateOne.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }
}
