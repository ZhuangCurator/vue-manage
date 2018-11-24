package com.baiding.vue.dao;

import com.baiding.vue.model.po.User;
import com.baiding.vue.model.vo.UserQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/9/24 10:31
 * @Email: liujiabaiding@foxmail.com
 */
public interface IndexDAO {

    User validateuser(@Param("email") String email, @Param("password") String password);

    int getUserCount(UserQueryCondition condition);

    List<User> getUserList(UserQueryCondition condition);

    User getUser(long id);

    void editUser(User user);

    void addUser(User user);

    void changeUserRemoved(@Param("id") long id, @Param("removed") int removed);

}
