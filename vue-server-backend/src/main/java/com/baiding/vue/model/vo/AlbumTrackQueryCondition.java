package com.baiding.vue.model.vo;


/**
 * @Author: BaiDing
 * @Date: 2018/10/18 11:17
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumTrackQueryCondition  {

    private Long albumId; //专辑id
    private Long id;  // 单集id
    private String title;  // 单集标题

    private int current = 1;
    private int pageSize = 50;
    private int start = 0;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
