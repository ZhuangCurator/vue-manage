package com.huahai.baiding.vue.dao;

import com.huahai.baiding.vue.entities.Hr;
import com.huahai.baiding.vue.entities.Role;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/9/24 10:31
 * @Email: liujiabaiding@foxmail.com
 */
public interface HrDAO {

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

}
