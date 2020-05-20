package com.cxy35.sample.springboot.mybatis.tkmybatis.mbg.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_user`")
public class TUser {
    /**
     * 主键id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "`username`")
    private String username;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 是否启用：1 启用；0 未启用
     */
    @Column(name = "`enabled`")
    private Boolean enabled;

    /**
     * 是否锁定：1 锁定；0 未锁定
     */
    @Column(name = "`locked`")
    private Boolean locked;

    /**
     * 地址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 昵称
     */
    @Column(name = "`nick_name`")
    private String nickName;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String USERNAME = "username";

    public static final String DB_USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String DB_PASSWORD = "password";

    public static final String ENABLED = "enabled";

    public static final String DB_ENABLED = "enabled";

    public static final String LOCKED = "locked";

    public static final String DB_LOCKED = "locked";

    public static final String ADDRESS = "address";

    public static final String DB_ADDRESS = "address";

    public static final String NICK_NAME = "nickName";

    public static final String DB_NICK_NAME = "nick_name";

    public static final String CREATE_TIME = "createTime";

    public static final String DB_CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                ", address='" + address + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否启用：1 启用；0 未启用
     *
     * @return enabled - 是否启用：1 启用；0 未启用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用：1 启用；0 未启用
     *
     * @param enabled 是否启用：1 启用；0 未启用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取是否锁定：1 锁定；0 未锁定
     *
     * @return locked - 是否锁定：1 锁定；0 未锁定
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 设置是否锁定：1 锁定；0 未锁定
     *
     * @param locked 是否锁定：1 锁定；0 未锁定
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}