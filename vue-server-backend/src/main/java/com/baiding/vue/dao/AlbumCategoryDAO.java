package com.baiding.vue.dao;

import com.baiding.vue.model.po.AlbumCategory;
import com.baiding.vue.model.vo.AlbumCategoryQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 15:03
 * @Email: liujiabaiding@foxmail.com
 */
public interface AlbumCategoryDAO {

    List<AlbumCategory> getAlbumCategory(AlbumCategoryQueryCondition condition);

    int getAlbumCategoryChildrenCount(long parentId);

    AlbumCategory getAlbumCategoryById(Long id);

    AlbumCategory queryAlbumCategoryByName(@Param("categoryName") String categoryName, @Param("id") Long id);

    void addAlbumCategory(AlbumCategory albumCategory);

    void editAlbumCategory(AlbumCategory albumCategory);

    void editAlbumCategoryStatus(@Param("id") Long id, @Param("status") Integer status);

    String getNameById(Long id);
}
