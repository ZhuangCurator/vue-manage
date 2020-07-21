package com.huahai.baiding.vue.dao;

import com.huahai.baiding.vue.model.po.Album;
import com.huahai.baiding.vue.model.vo.AlbumQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 15:03
 * @Email: liujiabaiding@foxmail.com
 */
public interface AlbumDAO {

    List<Album> getAllAlbum(AlbumQueryCondition condition);

    int getAllAlbumCount(AlbumQueryCondition condition);

    Album getAlbumById(Long id);

    void addlbum(Album album);

    void editALbum(Album album);

    void editAlbumStatus(@Param("id") Long id, @Param("removed") Integer removed);
}
