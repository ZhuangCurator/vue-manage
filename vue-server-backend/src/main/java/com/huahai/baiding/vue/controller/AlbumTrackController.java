package com.huahai.baiding.vue.controller;

import com.huahai.baiding.vue.model.BaseStatusParam;
import com.huahai.baiding.vue.model.Result;
import com.huahai.baiding.vue.model.vo.AlbumTrackParam;
import com.huahai.baiding.vue.model.vo.AlbumTrackQueryCondition;
import com.huahai.baiding.vue.service.AlbumTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: BaiDing
 * @Date: 2018/10/18 10:17
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
@RequestMapping("/albumTrack")
public class AlbumTrackController {

    @Autowired
    private AlbumTrackService albumTrackService;

    @GetMapping
    public Result getAllAlbumTrack(AlbumTrackQueryCondition condition){
        try{
            return Result.build(1,"专辑单集分页信息",albumTrackService.getAllAlbumTrack(condition));
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"查询单集分页信息出异常了哦~");
        }
    }

//    @GetMapping("/{id}")
//    @ApiOperation(value = "根据id查询单集信息")
//    public Result getAlbumTrack(@PathVariable("id") Long id){
//        try{
//            return Result.build(1,"专辑单集信息",albumTrackService.getAlbumTrack(id));
//        }catch(Exception e){
//            logger.info("ALbumTrackController#getAlbumTrack has exception:",e);
//            return Result.build(0,"查询单集信息出异常了哦~");
//        }
//    }
//
    @PostMapping
    public Result addAlbumTrack(@RequestBody AlbumTrackParam albumTrackParam){
        try{
            return albumTrackService.addAlbumTrack(albumTrackParam);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"添加单集出异常了哦~");
        }
    }

    @PutMapping
    public Result editAlbumTrack(@RequestBody AlbumTrackParam albumTrackParam){
        try{
            return albumTrackService.editAlbumTrack(albumTrackParam);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"编辑单集出异常了哦~");
        }
    }

    @PutMapping("/status")
    public Result editAlbumTrackStatus(@RequestBody BaseStatusParam param){
        try{
            return albumTrackService.editAlbumTrackStatus(param);
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"编辑单集状态出异常了哦~");
        }
    }

    @PutMapping("/batchStatus")
    public Result batcheditAlbumTrackStatus(@RequestBody BaseStatusParam param){
        try{
            return albumTrackService.batcheditAlbumTrackStatus(param.getIdList(), param.getStatus());
        }catch(Exception e){
            e.printStackTrace();
            return Result.build(0,"批量编辑单集状态出异常了哦~");
        }
    }
//
//    /**
//     * 生成操作日志
//     * @param module
//     * @param operate
//     * @param status
//     * @param note
//     */
//    private void generateOpLog(String module,String operate,int status,String note){
////        String name = getUserMameFromSession(getRequest());
//        String name = "测试用户";
//        BackendOpLog backendOpLog = new BackendOpLog(name,module, operate,status, note);
//        logService.saveLog(backendOpLog);
//    }
}
