package com.Marissa.FAQ.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class FileUploadController {
    //访问路径为：http://127.0.0.1:8080/file
    @RequestMapping("/upload")
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败,"+e.getMessage();
            }catch (IOException e) {
                e.printStackTrace();
                return "上传失败,"+e.getMessage();
            }

            return "上传成功";

        }else{

            return "上传失败，因为文件是空的.";

        }
    }

    /**

     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile

     * @param request

     * @return

     */
    @RequestMapping(value="/batch/upload", method=RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request){
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i =0; i< files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream =
                            new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream =  null;
                    return "You failed to upload " + i + " =>" + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " becausethe file was empty.";
            }
        }
        return "upload successful";
    }
}