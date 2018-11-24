package com.baiding.vue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: BaiDing
 * @Date: 2018/8/29 16:25
 * @Email: liujiabaiding@foxmail.com
 */
@Controller
@RequestMapping("pictureController")
public class PictureController{
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);
    private static final String UPLOAD_URL = "http://devws.image.cn/";

    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map uploadImage(@RequestParam("file") MultipartFile file){
        try{
            String fileName = file.getOriginalFilename();
            String extraName = fileName.substring(fileName.lastIndexOf("."));
            System.out.println("fileName: "+fileName);
//            String fileId = FileCopy.upload(file.getBytes(),fileName);
            String fileId = "1111111111111111";
            String url = UPLOAD_URL+fileId;
            return resultMap("SUCCESS",url,file.getSize(),fileId,fileName,extraName);
        }catch(Exception e){
            return resultMap("文件上传失败","",0,"","","");
        }
    }

    //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}

    private Map<String,Object> resultMap(String state,String url,long size,String title,String original,String type){
        Map<String ,Object> result = new HashMap<>();
        result.put("state",state);
        result.put("original",original);
        result.put("size",size);
        result.put("title",title);
        result.put("type",type);
        result.put("url", url);
        return result;
    }

}
