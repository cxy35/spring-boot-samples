package com.cxy35.sample.springboot.mybatis.mybatisplusmulti.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cxy35
 * @date 2021/1/20 14:05
 */
public interface UserMapper extends BaseMapper<User> {
    // 非必须，自定义 SQL（注解方式），支持多表联查
    // SQL 中不写 WHERE 关键字，且固定使用 ${ew.customSqlSegment}
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectListAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    // 非必须，自定义 SQL（XML 方式），支持多表联查
    List<User> selectListAll2(Wrapper<User> wrapper);
}
