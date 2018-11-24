package com.baiding.vue.service.impl;

import com.baiding.vue.config.RegularCheck;
import com.baiding.vue.dao.IndexDAO;
import com.baiding.vue.model.Page;
import com.baiding.vue.model.po.User;
import com.baiding.vue.model.vo.UserParam;
import com.baiding.vue.model.vo.UserQueryCondition;
import com.baiding.vue.service.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: BaiDing
 * @Date: 2018/10/26 13:30
 * @Email: liujiabaiding@foxmail.com
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexDAO indexDAO;

    @Override
    public User validateuser(String email, String password) {
        return indexDAO.validateuser(email, password);
    }

    @Override
    public Page<UserParam> userPage(UserQueryCondition condition) {

        if(condition.getUserIdArray() != null && !condition.getUserIdArray().equals("")){
            String[] userIdArray = condition.getUserIdArray().split(",");
            List<Long> collect = Arrays.stream(userIdArray).filter(userId -> RegularCheck.checkString(userId, RegularCheck.POSITIVE))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            long[] idArray = new long[collect.size()];
            for (int i = 0; i < collect.size(); i++) {
                idArray[i] = collect.get(i);
            }
            condition.setIdArray(idArray);
        }

        List<User> list = indexDAO.getUserList(condition);
        List<UserParam> resultList = new ArrayList<>();
        if(list != null && list.size() > 0){
            list.forEach(user -> {
                UserParam userParam = new UserParam();
                BeanUtils.copyProperties(user,userParam);
                resultList.add(userParam);
            });
        }
        int count = indexDAO.getUserCount(condition);
        return new Page.Builder<UserParam>().setPageNow(condition.getCurrent())
                .setRecordTotal(count).setList(resultList).build();
    }

    @Override
    public UserParam user(long id) {
        User user = indexDAO.getUser(id);
        UserParam userParam = new UserParam();
        BeanUtils.copyProperties(user,userParam);
        return userParam;
    }

    @Override
    public void editUser(UserParam userParam) {
        User user =new User();
        BeanUtils.copyProperties(userParam,user);
        indexDAO.editUser(user);
    }

    @Override
    public void addUser(UserParam userParam) {
        User user =new User();
        BeanUtils.copyProperties(userParam,user);
        user.setRegisterDate(new Date());
        indexDAO.addUser(user);
    }

    @Override
    public void changeUserRemoved(long id, int removed) {
        indexDAO.changeUserRemoved(id, removed);
    }

    @Override
    public List<UserParam> exportUser(UserQueryCondition condition) {
        if(condition.getUserIdArray() != null && !condition.getUserIdArray().equals("")){
            String[] userIdArray = condition.getUserIdArray().split(",");
            List<Long> collect = Arrays.stream(userIdArray).filter(userId -> RegularCheck.checkString(userId, RegularCheck.POSITIVE))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            long[] idArray = new long[collect.size()];
            for (int i = 0; i < collect.size(); i++) {
                idArray[i] = collect.get(i);
            }
            condition.setIdArray(idArray);
        }
        List<UserParam> resultList =new ArrayList<>();
        int count = indexDAO.getUserCount(condition);
        int pageSize = count % condition.getPageSize() == 0 ? count / condition.getPageSize() : count / condition.getPageSize() + 1;
        for(int i = 1; i <= pageSize; i++){
            condition.setCurrent(i);
            List<User> list = indexDAO.getUserList(condition);
            list.forEach(user -> {
                UserParam userParam = new UserParam();
                BeanUtils.copyProperties(user,userParam);
                resultList.add(userParam);
            });
        }
        return resultList;
    }

    @Override
    public void batchAddUser(List<UserParam> list) {
        list.forEach(userParam -> {
            User user =new User();
            BeanUtils.copyProperties(userParam,user);
            user.setRegisterDate(new Date());
            indexDAO.addUser(user);
        });
    }


}
