package com.baiding.vue.service;

import com.baiding.vue.dao.MenuDAO;
import com.baiding.vue.model.vhr.Hr;
import com.baiding.vue.model.vhr.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jun
 * @Date: 2020/7/14 上午10:09
 */
@Service
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public List<Menu> getAllMenusWithRole() {
        return menuDAO.getAllMenusWithRole();
    }

    public List<Menu> getMenusByHrId() {
        return menuDAO.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
