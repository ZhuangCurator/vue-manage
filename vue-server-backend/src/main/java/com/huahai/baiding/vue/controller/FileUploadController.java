package com.baiding.vue.controller;

import com.baiding.vue.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author: BaiDing
 * @Date: 2018/10/17 17:04
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/fileUpload/image")
    public Result upload(MultipartFile file){
        try {
//            String url = FastdfsUtil.upload(file.getBytes(), file.getOriginalFilename());
            // 这里就是上传图片的具体逻辑了，原本是通过fastdfs来上传图片的，这里就简单生成个字符串假装上传成功了吧
            String extraName = getExtraName(file.getOriginalFilename());
            String str = UUID.randomUUID().toString();
            String url = "http://dfs.image.com/" + str + extraName;
            return Result.build(1,"图片文件上传成功了",url);
        }catch(Exception e){
            logger.error("FileUploadController#upload exception: ",e);
            return Result.build(0,"图片文件上传失败了...");
        }
    }

    @PostMapping("/fileUpload/media")
    public Result uploadMedia(MultipartFile file){
        try {
//            String url = FastdfsUtil.uploadMedia(file.getBytes(), file.getOriginalFilename());
            // 这里就是上传图片的具体逻辑了，原本是通过fastdfs来上传图片的，这里就简单生成个字符串假装上传成功了吧
            String extraName = getExtraName(file.getOriginalFilename());
            String str = UUID.randomUUID().toString();
            String url = "http://dfs.image.com/" + str + extraName;
            return Result.build(1,"音频文件上传成功了",url);
        }catch(Exception e){
            logger.error("FileUploadController#uploadMedia exception: ",e);
            return Result.build(0,"音频文件上传失败了...");
        }
    }

    private static String getExtraName(String fileName){
        int i = fileName.lastIndexOf(".");
        String extraName = "";
        if (i > 0) {
            // 含有后缀名的文件
            extraName = fileName.substring(i);
        }
        return extraName;
    }
}
