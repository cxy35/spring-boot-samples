package com.cxy35.sample.springboot.mybatis.mybatisplusmulti;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
    public void update() {
        // 普通更新，按对象
        User user = new User();
        user.setAddress("上海");
        user.setUpdateTime(new Date());
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.likeRight(User::getUsername, "zhang");
        userService.update(user, wrapper);

        // 普通更新，按字段
        LambdaUpdateWrapper<User> wrapper2 = new LambdaUpdateWrapper<>();
        wrapper2.likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date());
        userService.update(null, wrapper);

        // 链式调用更新，按字段
        userService.lambdaUpdate().likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date()).update();
    }

    @Test
    public void getById() {
        User user = userService.getById(6);
        System.out.println(user);
    }

    @Test
    public void getOne() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.likeRight(User::getUsername, "zhang");
        // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
        User user = userService.getOne(wrapper, false);
        System.out.println(user);
    }

    @Test
    public void listByMap() {
        // 根据 map 中指定的列名和列值进行等值匹配查找
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("address", "杭州");
        List<User> users = userService.listByMap(columnMap);
        users.forEach(System.out::println);
    }

    @Test
    public void list() {
        // 查询所有
        List<User> allUser = userService.list();
        allUser.forEach(System.out::println);

        // 字段作为条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", "zhang");
        List<User> users = userService.list(wrapper);
        users.forEach(System.out::println);

        // 实体对象作为条件（默认会以实体对象中的非空属性，构建等值匹配的 WHERE 条件，可通过在实体类的字段上配置 @TableField 注解中的 condition 属性进行改变）
        User user = new User();
        user.setUsername("zhang");
        QueryWrapper<User> wrapper2 = new QueryWrapper<>(user);
        List<User> users2 = userService.list(wrapper2);
        users2.forEach(System.out::println);

        // 链式调用查询
        List<User> users3 = userService.lambdaQuery().likeRight(User::getUsername, "zhang").list();
        users3.forEach(System.out::println);
    }

    @Test
    public void page() {
        Page<User> page = new Page<>(1, 3);
        // Page<User> page = new Page<>(1, 3, false); // 不查总记录数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("id", 1, 20).eq("enabled", 1).orderByDesc("id");
        // wrapper.lambda().between(User::getId, 1, 20).eq(User::getEnabled, 1).orderByDesc(User::getId);
        // LambdaQueryWrapper<User> wrapper2 = new LambdaQueryWrapper<>();
        // LambdaQueryWrapper<User> wrapper2 = Wrappers.<User>lambdaQuery();
        // wrapper2.between(User::getId, 1, 20).eq(User::getEnabled, 1).orderByDesc(User::getId);
        Page<User> pageResult = userService.page(page, wrapper);
        System.out.println("总记录数：" + pageResult.getTotal());
        System.out.println("总页数：" + pageResult.getPages());
        System.out.println("当前页：" + pageResult.getCurrent());
        System.out.println("每页记录数：" + pageResult.getSize());
        System.out.println("是否存在上一页：" + pageResult.hasPrevious());
        System.out.println("是否存在下一页：" + pageResult.hasNext());
        // 获取分页查询结果
        List<User> records = pageResult.getRecords();
        records.forEach(System.out::println);
    }

    @Test
    // lambda 条件构造器，支持 lambda 表达式，可以不必像普通条件构造器一样，以字符串形式指定列名，它可以直接以实体类的方法引用来指定列，比较优雅
    public void testLambda() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        // LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getAddress, "杭州").likeRight(User::getUsername, "zhang");
        List<User> users = userService.list(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    // 链式 lambda 条件构造器，代码写起来非常简洁
    public void testChain() {
        // 查询
        List<User> users = userService.lambdaQuery().eq(User::getAddress, "杭州").likeRight(User::getUsername, "zhang").list();
        users.forEach(System.out::println);

        // 更新
        userService.lambdaUpdate().likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date()).update();

        // 删除
        userService.lambdaUpdate().eq(User::getEnabled, false).remove();
    }
}
