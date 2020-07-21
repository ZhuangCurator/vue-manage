package com.huahai.baiding.vue.service;

import com.huahai.baiding.vue.model.Page;
import com.huahai.baiding.vue.model.po.User;
import com.huahai.baiding.vue.model.vo.UserParam;
import com.huahai.baiding.vue.model.vo.UserQueryCondition;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/26 13:28
 * @Email: liujiabaiding@foxmail.com
 */
public interface IndexService {

    User validateuser(String email, String password);

    Page<UserParam> userPage(UserQueryCondition condition);

    UserParam user(long id);

    void editUser(UserParam userParam);

    void addUser(UserParam userParam);

    void changeUserRemoved(long id, int removed);

    List<UserParam> exportUser(UserQueryCondition condition);

    void batchAddUser(List<UserParam> list);
}
