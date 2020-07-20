package com.baiding.vue.dao;

import com.baiding.vue.entities.Hr;
import com.baiding.vue.entities.Role;
import com.baiding.vue.model.po.User;
import com.baiding.vue.model.vo.UserQueryCondition;
import org.apache.ibatis.annotations.Param;

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
