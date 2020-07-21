package com.huahai.baiding.vue.controller;


import com.huahai.baiding.vue.model.BaseStatusParam;
import com.huahai.baiding.vue.model.Result;
import com.huahai.baiding.vue.model.vo.AlbumCategoryParam;
import com.huahai.baiding.vue.model.vo.AlbumCategoryQueryCondition;
import com.huahai.baiding.vue.service.AlbumCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BaiDing
 * @Date: 2018/10/16 14:31
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
@RequestMapping("/albumCategory")
public class AlbumCategoryController {

    @Autowired
    private AlbumCategoryService albumCategoryService;

    @GetMapping
    public Result getAllAlbumCategory(AlbumCategoryQueryCondition condition){
        return Result.build(1,"专辑子分类信息",albumCategoryService.getAllCategory(condition));
    }

    @PostMapping
    public Result  addAlbumCategory(@RequestBody  AlbumCategoryParam param){
        try{
            return albumCategoryService.addAlbumCategory(param);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"添加专辑分类出异常了哦~");
        }
    }

    @PutMapping
    public Result  editAlbumCategory(@RequestBody AlbumCategoryParam param){
        try{
            return albumCategoryService.editAlbumCategory(param);
        }catch(Exception e){
            return Result.build(0,"编辑专辑分类出异常了哦~");
        }
    }

    @PutMapping("/categoryStatus")
    public Result editAlbumCategoryStatus(@RequestBody BaseStatusParam param){
        try{
            return albumCategoryService.editAlbumCategoryStatus(param.getId(),param.getStatus());
        }catch(Exception e){
            return Result.build(0,"编辑专辑分类上下架状态出异常了哦~");
        }
    }

}
