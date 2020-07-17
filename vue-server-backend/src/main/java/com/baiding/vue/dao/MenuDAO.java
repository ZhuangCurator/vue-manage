package com.baiding.vue.dao;

import com.baiding.vue.model.vhr.Menu;

import java.util.List;

/**
 * @Author: Jun
 * @Date: 2020/7/14 上午10:06
 */
public interface MenuDAO {
    List<Menu> getAllMenusWithRole();

    List<Menu> getMenusByHrId(Integer hrid);
}
