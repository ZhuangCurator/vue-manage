package com.huahai.baiding.vue.model;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 17:27
 * @Email: liujiabaiding@foxmail.com
 *
 * 提供给各模块做启用停用的通用类
 */
public class BaseStatusParam {

    private Long id;
    private Integer status;

    private String idArray; // 以逗号分隔的id集合，用于处理批量上下架、启用、禁用等问题

    private List<Long> idList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdArray() {
        return idArray;
    }

    public void setIdArray(String idArray) {
        this.idArray = idArray;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}
