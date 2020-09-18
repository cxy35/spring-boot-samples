package com.cxy35.sample.springboot.mybatis.tkmybatis;

import com.cxy35.sample.springboot.mybatis.tkmybatis.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.tkmybatis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBootMybatisTkmybatisApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insertSelective() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setAddress("杭州");
        user.setNickName("zs");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
    }

    @Test
    public void deleteByPrimaryKey() {
        userMapper.deleteByPrimaryKey(2);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        User user = new User();
        user.setId(2);
        user.setUsername("zhangsan2");
        user.setPassword("654321");
        user.setNickName("zs2");
        user.setAddress("上海");
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(4);
        System.out.println(user);
    }

    @Test
    public void selectAll() {
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }

    @Test
    public void selectByExample() {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("username", "zhangsan%");
        criteria.andEqualTo("address", "杭州");
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users);
    }

    @Test
    public void selectByRoleId() {
        List<User> users = userMapper.selectByRoleId(2);
        System.out.println(users);
    }
}
