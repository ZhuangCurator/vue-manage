package com.huahai.baiding.vue.controller;

import com.huahai.baiding.vue.model.BaseStatusParam;
import com.huahai.baiding.vue.model.Result;
import com.huahai.baiding.vue.model.vo.AlbumParam;
import com.huahai.baiding.vue.model.vo.AlbumQueryCondition;
import com.huahai.baiding.vue.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: BaiDing
 * @Date: 2018/10/27 14:34
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public Result getAllAlbum(AlbumQueryCondition condition, HttpServletRequest request){
        try{
            return Result.build(1,"专辑分页信息",albumService.getAllAlbum(condition));
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"查询专辑分页信息出异常了哦~");
        }
    }

    @GetMapping("/{id}")
    public Result getAlbum(@PathVariable("id") Long id){
        try{
            return Result.build(1,"专辑信息",albumService.getAlbum(id));
        }catch(Exception e){
            return Result.build(0,"查询专辑信息出异常了哦~");
        }
    }

    @PostMapping
    public Result  addAlbum(@RequestBody AlbumParam albumParam){
        try{
            return albumService.addlbum(albumParam);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"添加专辑出异常了哦~");
        }
    }

    @PutMapping
    public Result  editAlbum(@RequestBody AlbumParam albumParam){
        try{
            return albumService.editAlbum(albumParam);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"编辑专辑出异常了哦~");
        }
    }

    @PutMapping("/status")
    public Result editAlbumStatus(@RequestBody BaseStatusParam param){
        try{
            return albumService.editAlbumStatus(param.getId(),param.getStatus());

        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"编辑专辑状态出异常了哦~");
        }
    }
}
