package com.cxy35.sample.springboot.jdbctemplate.service;

import com.cxy35.sample.springboot.jdbctemplate.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 新增
    public int addUser(User user) {
        return jdbcTemplate.update("insert into t_user (username,address) values (?,?);", user.getUsername(), user.getAddress());
    }

    // 新增，主键回填
    public int addUser2(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int r = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into t_user (username,address) values (?,?);", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return r;
    }

    // 删除
    public int deleteUserById(Integer id) {
        return jdbcTemplate.update("delete from t_user where id=?", id);
    }

    // 修改
    public int updateUserById(User user) {
        return jdbcTemplate.update("update t_user set username=?,address=? where id=?", user.getUsername(), user.getAddress(), user.getId());
    }

    // 查询，使用 RowMapper 手动实现数据库字段和对象属性的映射
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from t_user", new RowMapper<User>() {
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

    // 查询，使用 BeanPropertyRowMapper 简单实现，前提是数据库字段和对象属性名称一致
    public List<User> getAllUsers2() {
        return jdbcTemplate.query("select * from t_user", new BeanPropertyRowMapper<>(User.class));
    }
}