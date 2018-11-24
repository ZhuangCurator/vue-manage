package com.baiding.vue.dao;

import com.baiding.vue.model.po.AlbumTrack;
import com.baiding.vue.model.vo.AlbumTrackQueryCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 15:03
 * @Email: liujiabaiding@foxmail.com
 */
public interface AlbumTrackDAO {

    List<AlbumTrack> getAllAlbumTrack(AlbumTrackQueryCondition condition);

    AlbumTrack getAlbumTrackById(Long id);

    int getAlbumTrackCount(AlbumTrackQueryCondition condition);

    void addAlbumTrack(AlbumTrack albumTrack);

    void editAlbumTrack(AlbumTrack albumTrack);

    void editAlbumTrackStatus(@Param("id") Long id, @Param("removed") Integer removed);
}
