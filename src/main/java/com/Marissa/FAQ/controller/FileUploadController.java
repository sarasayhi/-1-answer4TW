package com.Marissa.FAQ.controller;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
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

    @RequestMapping("/test")
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
}