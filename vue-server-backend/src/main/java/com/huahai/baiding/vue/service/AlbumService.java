package com.huahai.baiding.vue.service;

import com.huahai.baiding.vue.model.Page;
import com.huahai.baiding.vue.model.Result;
import com.huahai.baiding.vue.model.vo.AlbumParam;
import com.huahai.baiding.vue.model.vo.AlbumQueryCondition;

/**
 * @Author: BaiDing
 * @Date: 2018/10/17 14:45
 * @Email: liujiabaiding@foxmail.com
 */
public interface AlbumService {

    Page<AlbumParam> getAllAlbum(AlbumQueryCondition condition);

    AlbumParam getAlbum(Long id);

    Result addlbum(AlbumParam albumParam);

    Result editAlbum(AlbumParam albumParam);

    Result editAlbumStatus(Long id,Integer removed);


}
