package com.cxy35.sample.springboot.mybatis.mybatisplusmulti;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.mapper.UserMapper;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
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
    public void update() {
        // 普通更新，按对象
        User user = new User();
        user.setAddress("上海");
        user.setUpdateTime(new Date());
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.likeRight(User::getUsername, "zhang");
        userMapper.update(user, wrapper);

        // 普通更新，按字段
        LambdaUpdateWrapper<User> wrapper2 = new LambdaUpdateWrapper<>();
        wrapper2.likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date());
        userMapper.update(null, wrapper);

        // 链式调用更新，按字段
        LambdaUpdateChainWrapper<User> wrapper3 = new LambdaUpdateChainWrapper<>(userMapper);
        wrapper3.likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date()).update();
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(5);
        System.out.println(user);
    }

    @Test
    public void selectByMap() {
        // 根据 map 中指定的列名和列值进行等值匹配查找
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("address", "杭州");
        List<User> users = userMapper.selectByMap(columnMap);
        users.forEach(System.out::println);
    }

    @Test
    public void selectList() {
        // 查询所有
        List<User> allUser = userMapper.selectList(null);
        allUser.forEach(System.out::println);

        // 字段作为条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", "zhang");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

        // 实体对象作为条件（默认会以实体对象中的非空属性，构建等值匹配的 WHERE 条件，可通过在实体类的字段上配置 @TableField 注解中的 condition 属性进行改变）
        User user = new User();
        user.setUsername("zhang");
        QueryWrapper<User> wrapper2 = new QueryWrapper<>(user);
        List<User> users2 = userMapper.selectList(wrapper2);
        users2.forEach(System.out::println);

        // 链式调用查询
        LambdaQueryChainWrapper<User> wrapper3 = new LambdaQueryChainWrapper<>(userMapper);
        List<User> users3 = wrapper3.likeRight(User::getUsername, "zhang").list();
        users3.forEach(System.out::println);
    }

    @Test
    public void selectPage() {
        Page<User> page = new Page<>(1, 3);
        // Page<User> page = new Page<>(1, 3, false); // 不查总记录数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("id", 1, 20).eq("enabled", 1).orderByDesc("id");
        // wrapper.lambda().between(User::getId, 1, 20).eq(User::getEnabled, 1).orderByDesc(User::getId);
        // LambdaQueryWrapper<User> wrapper2 = new LambdaQueryWrapper<>();
        // LambdaQueryWrapper<User> wrapper2 = Wrappers.<User>lambdaQuery();
        // wrapper2.between(User::getId, 1, 20).eq(User::getEnabled, 1).orderByDesc(User::getId);
        Page<User> pageResult = userMapper.selectPage(page, wrapper);
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
    // 根据 wrapper 条件，查询记录，将查询结果封装为一个 Map，Map 的 key 为结果的列，value 为值
    public void selectMaps() {
        // 使用场景：只查部分列
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username", "address").likeRight("username", "zhang");
        List<Map<String, Object>> users = userMapper.selectMaps(wrapper);
        users.forEach(System.out::println);

        // 使用场景：进行数据统计（表中需增加 age 字段）
        QueryWrapper<User> wrapper2 = new QueryWrapper<>();
        wrapper2.select("address", "avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("address").having("sum(age) < {0}", 500);
        List<Map<String, Object>> users2 = userMapper.selectMaps(wrapper);
        users2.forEach(System.out::println);
    }

    @Test
    // 只会返回第一个字段（第一列）的值，其他字段会被舍弃
    public void selectObjs() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username").likeRight("username", "zhang");
        List<Object> users = userMapper.selectObjs(wrapper);
        // 得到的结果，只封装了第一列的id
        users.forEach(System.out::println);
    }

    @Test
    // 查询满足条件的总数，注意，使用这个方法，不能调用 QueryWrapper 的 select 方法设置要查询的列了，这个方法会自动添加 `select count(1)`
    public void selectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", "zhang");
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    // 条件构造器的诸多方法中，均可以指定一个 boolean 类型的参数 condition，用来决定该条件是否加入最后生成的WHERE语句中
    public void testCondition() {
        String username = "zhang";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight(username != null && username != "", "username", username);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    // allEq方法传入一个map，用来做等值匹配
    public void testAllEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("address", "杭州");
        param.put("username", "zhangsan");
        param.put("enabled", null);
        wrapper.allEq(param, false); // 忽略 map 中 value 为 null 的元素
        wrapper.allEq((k, v) -> !"address".equals(k), param); // 过滤掉 map 中 key 为 name 的元素
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    // lambda 条件构造器，支持 lambda 表达式，可以不必像普通条件构造器一样，以字符串形式指定列名，它可以直接以实体类的方法引用来指定列，比较优雅
    public void testLambda() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        // LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getAddress, "杭州").likeRight(User::getUsername, "zhang");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    // 链式 lambda 条件构造器，代码写起来非常简洁
    public void testChain() {
        // 查询
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<>(userMapper);
        List<User> users2 = wrapper.eq(User::getAddress, "杭州").likeRight(User::getUsername, "zhang").list();
        users2.forEach(System.out::println);

        // 更新
        LambdaUpdateChainWrapper<User> wrapper2 = new LambdaUpdateChainWrapper<>(userMapper);
        wrapper2.likeRight(User::getUsername, "zhang").set(User::getAddress, "上海").set(User::getUpdateTime, new Date()).update();

        // 删除
        LambdaUpdateChainWrapper<User> wrapper3 = new LambdaUpdateChainWrapper<>(userMapper);
        wrapper3.eq(User::getEnabled, false).remove();
    }
}
