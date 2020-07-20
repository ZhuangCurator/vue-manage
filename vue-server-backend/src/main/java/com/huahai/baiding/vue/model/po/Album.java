package com.baiding.vue.model.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: BaiDing
 * @Date: 2018/10/17 14:21
 * @Email: liujiabaiding@foxmail.com
 */
public class Album implements Serializable {

    private Long id;
    private String anchor; // 播主昵称
    private Long  categoryId; // 分类id
    private String author; // 作者
    private String title; // 标题
    private String titleImageUrl; // 标题图片
    private String coverImageUrl; // 专辑封面
    private String summary; // 简介
    private Long playedCount; // 播放次数
    private String description; // 描述
    private Long subscriptionCount; // 订阅数
    private Integer trackCount = 0; // 单集数量
    private String nation; // 国家
    private Integer hot; // 热门指数
    private Integer free = -1; // 是否免费
    private Integer finished = -1; // 更新状态
    private Integer commentable = -1; // 是否可以评论
    private String rssAddress; // rss地址
    private Integer removed; // 上下架状态
    private Date updateTime; // 更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImageUrl() {
        return titleImageUrl;
    }

    public void setTitleImageUrl(String titleImageUrl) {
        this.titleImageUrl = titleImageUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(Long playedCount) {
        this.playedCount = playedCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(Long subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public Integer getCommentable() {
        return commentable;
    }

    public void setCommentable(Integer commentable) {
        this.commentable = commentable;
    }

    public String getRssAddress() {
        return rssAddress;
    }

    public void setRssAddress(String rssAddress) {
        this.rssAddress = rssAddress;
    }

    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
