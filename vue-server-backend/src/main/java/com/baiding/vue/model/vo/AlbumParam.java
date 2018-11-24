package com.baiding.vue.model.vo;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: BaiDing
 * @Date: 2018/10/17 14:36
 * @Email: liujiabaiding@foxmail.com
 */
public class AlbumParam implements Serializable{

    private Long id;
    private String title; // 标题
    private String anchor; // 播主昵称
    private String nation; // 国家
    private Long  categoryId; // 分类id
    private String categoryName; // 分类名称
    private String author; // 作者
    private Long subscriptionCount; // 订阅数
    private Long playedCount; // 播放次数
    private Integer hot; // 热度
    private String rssAddress; // rss地址
    private String titleImageUrl; // 标题图片
    private String coverImageUrl; // 专辑封面
    private Integer removed; // 上下架状态
    private String summary; // 简介
    private String description; // 描述

    private Date updateTime;

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

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(Long subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public Long getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(Long playedCount) {
        this.playedCount = playedCount;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getRssAddress() {
        return rssAddress;
    }

    public void setRssAddress(String rssAddress) {
        this.rssAddress = rssAddress;
    }

    public String getTitleImageUrl() {
        if(StringUtils.isEmpty(titleImageUrl)){
            return coverImageUrl;
        }else{
            return titleImageUrl;
        }
    }

    public void setTitleImageUrl(String titleImageUrl) {
        if(StringUtils.isEmpty(titleImageUrl)){
            this.titleImageUrl = getCoverImageUrl();
        }else {
            this.titleImageUrl = titleImageUrl;
        }
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
