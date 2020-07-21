package com.huahai.baiding.vue.model.vo;


/**
 * @Author: BaiDing
 * @Date: 2018/10/16 14:40
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumCategoryQueryCondition {

    private Long parentId = 0L;
    private Integer removed = -1;
    private Integer current = 1;
    private Integer pageSize = 100;

    private Integer all = 0; // 将此分类下的所有层级分类都查出来 1 都查 0 不查

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
