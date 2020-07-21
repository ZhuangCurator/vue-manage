package com.huahai.baiding.vue.model.vo;

/**
 * @Author: BaiDing
 * @Date: 2018/10/17 14:33
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumQueryCondition {

    private Long id;
    private String albumTitle;
    private Long albumCategoryId;
    private String anchor;

    private int current = 1;
    private int pageSize = 50;
    private int start = 0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Long getAlbumCategoryId() {
        return albumCategoryId;
    }

    public void setAlbumCategoryId(Long albumCategoryId) {
        this.albumCategoryId = albumCategoryId;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
        setStart();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    private void setStart() {
        this.start = this.current > 1 ? (this.current - 1) * pageSize : 0;
    }


}
