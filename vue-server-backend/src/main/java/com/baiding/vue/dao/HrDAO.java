package com.baiding.vue.dao;

import com.baiding.vue.model.vhr.Hr;
import com.baiding.vue.model.vhr.Role;

import java.util.List;

/**
 * @Author: Jun
 * @Date: 2020/7/13 下午11:25
 */
public interface HrDAO {

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);
}
