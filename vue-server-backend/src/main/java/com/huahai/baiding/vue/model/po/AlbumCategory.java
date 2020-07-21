package com.huahai.baiding.vue.model.po;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 14:32
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumCategory implements Serializable{

    private Long id;
    private Long parentId;
    private String categoryName;
    private Integer rank;
    private String imageUrl;
    private String desc;
    private Integer removed;

    private int child; // 下面是否有子分类 1 有 0 没有
    private List<AlbumCategory> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public List<AlbumCategory> getChildren() {
        return children;
    }

    public void setChildren(List<AlbumCategory> children) {
        this.children = children;
    }
}
