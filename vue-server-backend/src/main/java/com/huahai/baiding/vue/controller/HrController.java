package com.baiding.vue.controller;

import com.baiding.vue.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jun
 * @Date: 2020/7/18
 */
@RestController
public class HrController {

    @Autowired
    HrService hrService;


}
