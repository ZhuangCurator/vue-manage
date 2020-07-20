package com.baiding.vue.service.impl;

import com.baiding.vue.dao.AlbumCategoryDAO;
import com.baiding.vue.dao.AlbumDAO;
import com.baiding.vue.model.Page;
import com.baiding.vue.model.Result;
import com.baiding.vue.model.po.Album;
import com.baiding.vue.model.vo.AlbumParam;
import com.baiding.vue.model.vo.AlbumQueryCondition;
import com.baiding.vue.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/27 14:36
 * @Email: liujiabaiding@foxmail.com
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private AlbumCategoryDAO albumCategoryDAO;

    @Override
    public Page<AlbumParam> getAllAlbum(AlbumQueryCondition condition) {
        int count = albumDAO.getAllAlbumCount(condition);
        List<Album>  list = albumDAO.getAllAlbum(condition);
        List<AlbumParam> resultList = new ArrayList<>();
        convertAlbum(resultList,list);
        return new Page.Builder<AlbumParam>().setPageNow(condition.getCurrent())
                .setRecordTotal(count).setList(resultList).build();
    }

    @Override
    public AlbumParam getAlbum(Long id) {
        Album album = albumDAO.getAlbumById(id);
        AlbumParam albumParam = new AlbumParam();
        albumParam.setId(album.getId());
        albumParam.setAnchor(album.getAnchor());
        albumParam.setCategoryId(album.getCategoryId());
        String name = albumCategoryDAO.getNameById(albumParam.getCategoryId());
        albumParam.setCategoryName(name);
        albumParam.setSubscriptionCount(album.getSubscriptionCount());
        albumParam.setCoverImageUrl(album.getCoverImageUrl());
        albumParam.setDescription(album.getDescription());
        albumParam.setAuthor(album.getAuthor());
        albumParam.setHot(album.getHot());
        albumParam.setNation(album.getNation());
        albumParam.setPlayedCount(album.getPlayedCount());
        albumParam.setRemoved(album.getRemoved());
        albumParam.setRssAddress(album.getRssAddress());
        albumParam.setSummary(album.getSummary());
        albumParam.setTitle(album.getTitle());
        albumParam.setTitleImageUrl(album.getTitleImageUrl());
        albumParam.setUpdateTime(album.getUpdateTime());
        return albumParam;
    }

    @Override
    public Result addlbum(AlbumParam albumParam) {
        Album album = new Album();
        album.setAnchor(albumParam.getAnchor());
        album.setCategoryId(albumParam.getCategoryId());
        album.setCoverImageUrl(albumParam.getCoverImageUrl());
        album.setDescription(albumParam.getDescription());
        album.setAuthor(albumParam.getAuthor());
        album.setHot(albumParam.getHot());
        album.setNation(albumParam.getNation());
        album.setPlayedCount(albumParam.getPlayedCount());
        album.setRemoved(albumParam.getRemoved());
        album.setRssAddress(albumParam.getRssAddress());
        album.setSummary(albumParam.getSummary());
        album.setSubscriptionCount(albumParam.getSubscriptionCount());
        album.setTitle(albumParam.getTitle());
        album.setTitleImageUrl(albumParam.getTitleImageUrl());
        album.setUpdateTime(new Date());
        album.setFree(1);
        album.setFinished(1);
        album.setCommentable(1);
        albumDAO.addlbum(album);
        return Result.build(1,"专辑添加成功了...");
    }

    @Override
    public Result editAlbum(AlbumParam albumParam) {
        Album album = new Album();
        album.setId(albumParam.getId());
        album.setAnchor(albumParam.getAnchor());
        album.setCategoryId(albumParam.getCategoryId());
        album.setCoverImageUrl(albumParam.getCoverImageUrl());
        album.setDescription(albumParam.getDescription());
        album.setAuthor(albumParam.getAuthor());
        album.setHot(albumParam.getHot());
        album.setPlayedCount(albumParam.getPlayedCount());
        album.setSubscriptionCount(albumParam.getSubscriptionCount());
        album.setNation(albumParam.getNation());
        album.setRemoved(albumParam.getRemoved());
        album.setRssAddress(albumParam.getRssAddress());
        album.setSummary(albumParam.getSummary());
        album.setTitle(albumParam.getTitle());
        album.setTitleImageUrl(albumParam.getTitleImageUrl());
        album.setUpdateTime(new Date());
        albumDAO.editALbum(album);
        return Result.build(1,"专辑编辑成功了...");
    }

    @Override
    public Result editAlbumStatus(Long id, Integer removed) {
        albumDAO.editAlbumStatus(id, removed);
        return Result.build(1,"专辑状态编辑成功了...");
    }

    private void convertAlbum(List<AlbumParam> toList, List<Album> fromList){
        if(fromList != null && fromList.size()>0) {
            fromList.forEach(from -> {
                AlbumParam albumParam = new AlbumParam();
                albumParam.setId(from.getId());
                albumParam.setAnchor(from.getAnchor());
                albumParam.setCategoryId(from.getCategoryId());
                String name = albumCategoryDAO.getNameById(albumParam.getCategoryId());
                albumParam.setCategoryName(name);
                albumParam.setSubscriptionCount(from.getSubscriptionCount());
                albumParam.setCoverImageUrl(from.getCoverImageUrl());
                albumParam.setDescription(from.getDescription());
                albumParam.setAuthor(from.getAuthor());
                albumParam.setHot(from.getHot());
                albumParam.setNation(from.getNation());
                albumParam.setPlayedCount(from.getPlayedCount());
                albumParam.setRemoved(from.getRemoved());
                albumParam.setRssAddress(from.getRssAddress());
                albumParam.setSummary(from.getSummary());
                albumParam.setTitle(from.getTitle());
                albumParam.setTitleImageUrl(from.getTitleImageUrl());
                albumParam.setUpdateTime(from.getUpdateTime());
                toList.add(albumParam);
            });
        }
    }
}
