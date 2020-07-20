package com.baiding.vue.service.impl;

import com.baiding.vue.dao.AlbumTrackDAO;
import com.baiding.vue.model.BaseStatusParam;
import com.baiding.vue.model.Page;
import com.baiding.vue.model.Result;
import com.baiding.vue.model.po.AlbumTrack;
import com.baiding.vue.model.vo.AlbumTrackParam;
import com.baiding.vue.model.vo.AlbumTrackQueryCondition;
import com.baiding.vue.service.AlbumTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: BaiDing
 * @Date: 2018/10/18 11:19
 * @Email: liujiabaiding@foxmail.com
 */
@Service
public class AlbumTrackServiceImpl implements AlbumTrackService {

    @Autowired
    private AlbumTrackDAO albumTrackDAO;

    @Override
    public Page<AlbumTrackParam> getAllAlbumTrack(AlbumTrackQueryCondition condition) {
        int count = albumTrackDAO.getAlbumTrackCount(condition);
        List<AlbumTrack> albumTrackList = albumTrackDAO.getAllAlbumTrack(condition);
        List<AlbumTrackParam> resultList = new ArrayList<>();
        convertAlbumTrack(resultList,albumTrackList);
        return new Page.Builder<AlbumTrackParam>().setPageNow(condition.getCurrent())
                .setRecordTotal(count).setList(resultList).build();
    }

//    @Override
//    public AlbumTrackParam getAlbumTrack(Long id) {
//        AlbumTrack albumTrack = albumTrackDAO.getAlbumTrackById(id);
//        AlbumTrackParam param = new AlbumTrackParam();
//        BeanUtil.populate(albumTrack, param, true, true, true);
//        return param;
//    }

    @Override
    public Result addAlbumTrack(AlbumTrackParam albumTrackParam) {
        AlbumTrack albumTrack = new AlbumTrack();
        albumTrack.setTitle(albumTrackParam.getTitle());
        albumTrack.setAlbumId(albumTrackParam.getAlbumId());
        albumTrack.setPlayedCount(albumTrackParam.getPlayedCount());
        albumTrack.setTitleImageUrl(albumTrackParam.getTitleImageUrl());
        albumTrack.setImageUrl(albumTrackParam.getImageUrl());
        albumTrack.setMediaUrl(albumTrackParam.getMediaUrl());
        albumTrack.setRemoved(albumTrackParam.getRemoved());
        albumTrack.setUpdateTime(new Date());
        albumTrack.setReleaseTime(new Date());


        albumTrackDAO.addAlbumTrack(albumTrack);
        return Result.build(1,"单集添加成功了...");
    }

    @Override
    public Result editAlbumTrack(AlbumTrackParam albumTrackParam) {
        AlbumTrack albumTrack = new AlbumTrack();
        albumTrack.setId(albumTrackParam.getId());
        albumTrack.setTitle(albumTrackParam.getTitle());
        albumTrack.setImageUrl(albumTrackParam.getImageUrl());
        albumTrack.setTitleImageUrl(albumTrackParam.getTitleImageUrl());
        albumTrack.setMediaUrl(albumTrackParam.getMediaUrl());
        albumTrack.setRemoved(albumTrackParam.getRemoved());
        albumTrack.setUpdateTime(new Date());
        albumTrackDAO.editAlbumTrack(albumTrack);
        return Result.build(1,"单集编辑成功了...");
    }

    @Override
    public Result editAlbumTrackStatus(BaseStatusParam param) {
        albumTrackDAO.editAlbumTrackStatus(param.getId(),param.getStatus());
        return Result.build(1,"单集状态编辑成功了...");
    }

    @Override
    public Result batcheditAlbumTrackStatus(List<Long> idList, Integer removed) {
        if(idList != null && idList.size() > 0){
            for(Long id : idList){
                albumTrackDAO.editAlbumTrackStatus(id,removed);
            }
        }
        return Result.build(1,"单集状态批量编辑成功了...");
    }

    private void convertAlbumTrack(List<AlbumTrackParam> toList, List<AlbumTrack> fromList){
        if(fromList != null && fromList.size()>0) {
            fromList.forEach(from -> {
                AlbumTrackParam param = new AlbumTrackParam();
                param.setId(from.getId());
                param.setTitle(from.getTitle());
                param.setAlbumId(from.getAlbumId());
                param.setDuration(from.getDuration());
                param.setPlayedCount(from.getPlayedCount());
                param.setTitleImageUrl(from.getTitleImageUrl());
                param.setImageUrl(from.getImageUrl());
                param.setMediaUrl(from.getMediaUrl());
                param.setRemoved(from.getRemoved());
                param.setUpdateTime(from.getUpdateTime());
                toList.add(param);
            });
        }
    }
}
