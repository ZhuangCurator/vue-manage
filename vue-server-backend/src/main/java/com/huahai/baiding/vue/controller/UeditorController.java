package com.huahai.baiding.vue.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: BaiDing
 * @Date: 2018/8/30 11:58
 * @Email: liujiabaiding@foxmail.com
 */
@RestController
public class UeditorController {

    private static final Logger logger = LoggerFactory.getLogger(UeditorController.class);

    @RequestMapping("/ueditor/ueditorConfig")
    public void ueditorConfig(HttpServletRequest request, HttpServletResponse response, MultipartFile upfile) {

        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = "";
            String actionType = request.getParameter("action");
            if("uploadimage".equals(actionType) && !upfile.isEmpty()){
                // 做图片上传操作
                exec = uploadImage(upfile);

            }else{
                request.setCharacterEncoding( "utf-8" );
                exec = new ActionEnter(request, rootPath).exec();
            }
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("UeditorController#ueditorConfig exception:",e);
        }
    }

    private String uploadImage(MultipartFile file) {
        JSONObject jsonResult = null;
        try {
            String fileName = file.getOriginalFilename();
            String extraName = fileName.substring(fileName.lastIndexOf("."));
            // 这里就是上传图片的具体逻辑了，原本是通过fastdfs来上传图片的，这里就简单生成个字符串假装上传成功了吧
//            String url = FastdfsUtil.upload(file.getBytes(), file.getOriginalFilename());
            String str = UUID.randomUUID().toString();
            String url = "http://dfs.image.com/"+str+".png ";
            jsonResult = new JSONObject(resultMap("SUCCESS",url,file.getSize(),fileName,fileName,extraName));

        } catch (Exception e) {
            logger.warn("UeditorController#uploadImage exception:",e);
            jsonResult = new JSONObject(resultMap("文件上传失败","",0,"","",""));
        }
        return jsonResult.toString();
    }

    //{"state": "SUCCESS","original": "111.jpg","size": "124147","title": "1535961757878095151.jpg","type": ".jpg","url": "/1535961757878095151.jpg"}

    private Map<String,Object> resultMap(String state, String url, long size, String title, String original, String type){
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
