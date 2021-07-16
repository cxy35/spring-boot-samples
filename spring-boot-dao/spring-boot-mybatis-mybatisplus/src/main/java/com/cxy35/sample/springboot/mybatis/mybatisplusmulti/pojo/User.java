package com.cxy35.sample.springboot.mybatis.mybatisplusmulti.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 以实体对象作为条件查询时，配置该字段使用 like 进行拼接
    @TableField(condition = SqlCondition.LIKE)
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private String address;
    private String nickName;
    private Date createTime;
    private Date updateTime;
}
