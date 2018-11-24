package com.baiding.vue.service.impl;

import com.baiding.vue.dao.AlbumCategoryDAO;
import com.baiding.vue.model.Result;
import com.baiding.vue.model.po.AlbumCategory;
import com.baiding.vue.model.vo.AlbumCategoryParam;
import com.baiding.vue.model.vo.AlbumCategoryQueryCondition;
import com.baiding.vue.service.AlbumCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 14:42
 * @Email: liujiabaiding@foxmail.com
 */
@Service
public class AlbumCategoryServiceImpl implements AlbumCategoryService {

    @Autowired
    private AlbumCategoryDAO albumCategoryDAO;

    @Override
    public List<AlbumCategory> getAllCategory(AlbumCategoryQueryCondition condition) {
        List<AlbumCategory> list = albumCategoryDAO.getAlbumCategory(condition);
        for(AlbumCategory albumCategory : list){
            int childCount = albumCategoryDAO.getAlbumCategoryChildrenCount(albumCategory.getId());
            if(childCount > 0){
                albumCategory.setChild(1);
                if(condition.getAll() == 1) {
                    condition.setParentId(albumCategory.getId());
                    List<AlbumCategory> childrenList = this.getAllCategory(condition);
                    albumCategory.setChildren(childrenList);
                }
            }else{
                albumCategory.setChild(0);
            }
        }
        return list;
    }

    @Override
    public AlbumCategory getAlbumCategory(Long id) {
        return albumCategoryDAO.getAlbumCategoryById(id);
    }

    @Override
    public Result addAlbumCategory(AlbumCategoryParam param) {

        AlbumCategory category = albumCategoryDAO.queryAlbumCategoryByName(param.getCategoryName(),0L);
        if(category != null){
            return Result.build(0,"该商品分类已存在...");
        }

        AlbumCategory albumCategory = new AlbumCategory();
        albumCategory.setParentId(param.getParentId());
        albumCategory.setCategoryName(param.getCategoryName());
        albumCategory.setImageUrl(param.getImageUrl());
        albumCategory.setRank(param.getRank());
        albumCategory.setDesc(param.getDesc());
        // 默认停用
        albumCategory.setRemoved(1);
        albumCategoryDAO.addAlbumCategory(albumCategory);
        return Result.build(1,"添加成功了哦...");
    }

    @Override
    public Result editAlbumCategory(AlbumCategoryParam param) {
        AlbumCategory category = albumCategoryDAO.queryAlbumCategoryByName(param.getCategoryName(),param.getId());
        if(category != null){
            Result.build(0,"该商品分类已存在...");
        }
        AlbumCategory albumCategory = new AlbumCategory();
        albumCategory.setId(param.getId());
        albumCategory.setParentId(param.getParentId());
        albumCategory.setCategoryName(param.getCategoryName());
        albumCategory.setImageUrl(param.getImageUrl());
        albumCategory.setRank(param.getRank());
        albumCategory.setDesc(param.getDesc());
        albumCategoryDAO.editAlbumCategory(albumCategory);
        return Result.build(1,"编辑成功了哦...");
    }

    @Override
    public Result editAlbumCategoryStatus(Long id, Integer status) {
        albumCategoryDAO.editAlbumCategoryStatus(id, status);
        return Result.build(1,"编辑成功了哦...");
    }
}
