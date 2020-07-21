package com.huahai.baiding.vue.service;

import com.huahai.baiding.vue.dao.HrDAO;
import com.huahai.baiding.vue.entities.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: Jun
 * @Date: 2020/7/17
 */
@Service
public class HrService implements UserDetailsService {

    @Autowired
    private HrDAO hrDAO;

    /**
     * 此username既包含用户名又包含用户手机号
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrDAO.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("该用户名或手机号码不存在!");
        }
        hr.setRoles(hrDAO.getHrRolesById(hr.getId()));
        return hr;
    }
}
