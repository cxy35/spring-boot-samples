package com.zhengjian.sample.springboot.swagger2.controller;

import com.zhengjian.sample.springboot.swagger2.bean.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author zhengjian
 * @Date 2019-06-05 9:38
 */
@RestController
@Api(tags = "用户管理接口")
public class UserController {

    @GetMapping("/user")
    @ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("zhangsan");
        user.setAddress("HZ");
        return user;
    }

    @PutMapping("/user")
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "javaboy")
    })
//    @ApiIgnore
    public User updateUsernameById(String username, Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    @PostMapping("/user")
    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    public User addUser(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    public void deleteUserById(@PathVariable Long id) {

    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + name + " !";
    }
}
