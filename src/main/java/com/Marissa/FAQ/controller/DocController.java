package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.service.testService;
import com.Marissa.FAQ.utils.CommonUtils;
import com.Marissa.FAQ.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocController {
    @Autowired
    testService tesService;
    public String uploadImage(@RequestParam("file")MultipartFile file){
        try{
           String fileUrl = tesService.saveImage(file);
           if(fileUrl == null){
               return CommonUtils.getJSONString(1,"上传图片失败");
           }
           return CommonUtils.getJSONString(0,fileUrl);
        } catch (Exception e){
            LogUtil.error("上传图片失败" + e.getMessage());
            return CommonUtils.getJSONString(1,"上传失败");
        }
    }
}
