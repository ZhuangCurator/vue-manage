package com.baiding.vue.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: BaiDing
 * @Date: 2018/10/18 10:19
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumTrackParam {

    private Long id;
    private Long albumId; // 专辑id
    private String title; // 标题
    private Long duration = 0L; //时长
    private Long playedCount; // 播放次数
    private String titleImageUrl; // 标题图片
    private String imageUrl; // 大图
    private String mediaUrl; // 音频url
    private Integer removed;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(Long playedCount) {
        this.playedCount = playedCount;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public String getUpdateTime() {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
