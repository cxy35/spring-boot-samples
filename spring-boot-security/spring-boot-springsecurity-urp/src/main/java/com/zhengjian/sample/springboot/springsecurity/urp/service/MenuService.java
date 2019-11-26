package com.zhengjian.sample.springboot.springsecurity.urp.service;

import com.zhengjian.sample.springboot.springsecurity.urp.mapper.MenuMapper;
import com.zhengjian.sample.springboot.springsecurity.urp.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
