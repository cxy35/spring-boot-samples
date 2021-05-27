package com.cxy35.sample.springboot.mybatis.mybatisplusmulti;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setAddress("杭州");
        user.setNickName("zs");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.save(user);
    }

    @Test
    public void removeById() {
        userService.removeById(6);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(6);
        user.setUsername("zhangsan2");
        user.setPassword("654321");
        user.setNickName("zs2");
        user.setAddress("上海");
        user.setUpdateTime(new Date());
        userService.updateById(user);
    }

    @Test
    public void getById() {
        User user = userService.getById(6);
        System.out.println(user);
    }

    @Test
    public void list() {
        List<User> users = userService.list();
        System.out.println(users);
    }

    @Test
    public void listByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("address", "杭州");
        List<User> users = userService.listByMap(columnMap);
        System.out.println(users);
    }

    @Test
    public void page() {
        Page<User> page = new Page<>(1, 3);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.between("id", 1, 20).eq("enabled", 1).orderByDesc("id");
        // qw.lambda().between(User::getId, 1, 20).eq(User::getEnabled, 1).orderByDesc(User::getId);
        Page<User> pageResult = userService.page(page, qw);
        System.out.println("总记录数：" + pageResult.getTotal());
        System.out.println("当前页：" + pageResult.getCurrent());
        System.out.println("每页记录数：" + pageResult.getSize());
        System.out.println("获取总页数：" + pageResult.getPages());
        System.out.println("查询结果：" + pageResult.getRecords());
        System.out.println("是否存在上一页：" + pageResult.hasPrevious());
        System.out.println("是否存在下一页：" + pageResult.hasNext());
    }
}
