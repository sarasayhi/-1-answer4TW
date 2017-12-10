package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.repository.po.comment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class TestsController {
    @RequestMapping("/test")
    @ResponseBody
    public String entry(HttpServletRequest request) throws ServletException,IOException{
        //从request中获取流信息
        InputStream fileSource = request.getInputStream();
        String tmpPath = "E:/tmpFile";
        //tmpFile 指向临时文件
        File tmpFile = new File(tmpPath);
        // outputStream文件输出流指向这个临时文件
        FileOutputStream outputStream = new FileOutputStream(tmpFile);
        byte b[] = new byte[1024];
        int n;
        while ((n = fileSource.read(b)) != -1){
            outputStream.write(b,0,n);
        }
        //关闭输出流、输入流
        outputStream.close();
        fileSource.close();
        return "index";
    }

        @RequestMapping("/")
    public String t(){
        return "/index";
    }

    @RequestMapping("/md")
    public String md(){
        comment comme =new comment();
        return "etet";
    }
}
