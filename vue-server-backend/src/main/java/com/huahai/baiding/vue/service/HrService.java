package com.baiding.vue.service;

import com.baiding.vue.dao.HrDAO;
import com.baiding.vue.entities.Hr;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrDAO.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        hr.setRoles(hrDAO.getHrRolesById(hr.getId()));
        return hr;
    }
}
