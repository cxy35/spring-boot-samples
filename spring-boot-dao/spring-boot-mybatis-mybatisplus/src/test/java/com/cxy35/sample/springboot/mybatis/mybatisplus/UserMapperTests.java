package com.cxy35.sample.springboot.mybatis.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy35.sample.springboot.mybatis.mybatisplus.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserMapperTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setAddress("杭州");
        user.setNickName("zs");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
    }

    @Test
    public void deleteById() {
        userMapper.deleteById(5);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(5);
        user.setUsername("zhangsan2");
        user.setPassword("654321");
        user.setNickName("zs2");
        user.setAddress("上海");
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(5);
        System.out.println(user);
    }

    @Test
    public void selectList() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("address", "杭州");
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }

    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 3);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.between("id", 1, 20).eq("enabled", 1).orderByDesc("id");
        Page<User> pageResult = userMapper.selectPage(page, qw);
        System.out.println("总记录数：" + pageResult.getTotal());
        System.out.println("当前页：" + pageResult.getCurrent());
        System.out.println("每页记录数：" + pageResult.getSize());
        System.out.println("获取总页数：" + pageResult.getPages());
        System.out.println("查询结果：" + pageResult.getRecords());
        System.out.println("是否存在上一页：" + pageResult.hasPrevious());
        System.out.println("是否存在下一页：" + pageResult.hasNext());
    }
}
