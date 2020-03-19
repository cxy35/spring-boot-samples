package com.cxy35.sample.springboot.springcache.ehcache.service;

import com.cxy35.sample.springboot.springcache.ehcache.pojo.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
// 指定缓存名称
@CacheConfig(cacheNames = "cache-user")
public class UserService {

    // 注意要在启动类上配置开启缓存 @EnableCaching

    // 默认缓存的 key 为所有参数的值（可通过 key 或 keyGenerator 修改），缓存的 value 为方法的返回值
    // cache-user::1
    // 查询
    @Cacheable
    // @Cacheable(key = "#id")
    // @Cacheable(key = "#root.methodName")
    // @Cacheable(keyGenerator = "myKeyGenerator")
    public User getUserById(Integer id) {
        System.out.println("getUserById >>> " + id);
        User user = new User();
        user.setId(id);
        return user;
    }

    // 删除
    @CacheEvict
    public void deleteUserById(Integer id) {
        System.out.println("deleteUserById >>> " + id);
    }

    // 更新
    @CachePut(key = "#user.id")
    public User updateUserById(User user) {
        return user;
    }
}
