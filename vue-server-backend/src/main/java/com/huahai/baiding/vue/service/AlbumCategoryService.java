package com.baiding.vue.service;


import com.baiding.vue.model.Result;
import com.baiding.vue.model.po.AlbumCategory;
import com.baiding.vue.model.vo.AlbumCategoryParam;
import com.baiding.vue.model.vo.AlbumCategoryQueryCondition;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 14:36
 * @Email: liujiabaiding@foxmail.com
 */
public interface AlbumCategoryService {

    List<AlbumCategory> getAllCategory(AlbumCategoryQueryCondition condition);

    AlbumCategory getAlbumCategory(Long id);

    Result addAlbumCategory(AlbumCategoryParam param);

    Result editAlbumCategory(AlbumCategoryParam param);

    Result editAlbumCategoryStatus(Long id, Integer status);
}
