package com.baiding.vue.controller;

import com.baiding.vue.model.vhr.Menu;
import com.baiding.vue.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jun
 * @Date: 2020/7/14 上午10:20
 */
@RestController
@RequestMapping("/system/config")
public class MenuController {

    @Autowired
    MenuService menuService;
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId();
    }
}
