package com.Marissa.FAQ.controller;

import com.Marissa.FAQ.controller.vo.UploadModel;
import com.Marissa.FAQ.repository.po.Doc;
import com.Marissa.FAQ.repository.po.DocContent;
import com.Marissa.FAQ.service.DocContentService;
import com.Marissa.FAQ.service.DocService;
import com.Marissa.FAQ.utils.CommonUtils;
import com.Marissa.FAQ.utils.ConstantParameter;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Api(value = "文档操作",tags = "文档")
@RestController
public class RestUploadController {

    @Resource
    private DocService docService;

    @Resource
    private DocContentService docContentService;

    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);

    @PostMapping(value = "/list/doc",produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "获取文档列表",notes = "获取文档列表")
    public ResponseEntity<List<Doc>> getPageList(@RequestBody String params,HttpServletRequest request) throws FileNotFoundException {
//        @RequestParam("params")  @RequestBody
        String name = request.getServletContext().getContextPath();
//                ResourceUtils.getURL("classpath:test").toString();
//                ClassUtils.getDefaultClassLoader().getResource("").getPath();

        Pageable pageable = new PageRequest(1,1, Sort.Direction.DESC,"id");
        List<Doc> list = docService.getPageList(pageable);
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String path1 = "";
        try {
            path1 = resourceLoader.getResource("file:").getFile().getAbsolutePath() + File.separator +"upload"+ File.separator ;

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.size() > 0 ){
            list.get(0).setTags(path1);
        }
        return new ResponseEntity<List<Doc>>(list,HttpStatus.OK);
    }
    // Multiple file upload
    @PostMapping("/api/upload/multi")
    public ResponseEntity<?> uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles,HttpServletRequest request) {

        logger.debug("Multiple file upload!");

        String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        if (StringUtils.isEmpty(uploadedFileName)) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
//            upload(Arrays.asList(uploadfiles),request);
        try {
            saveUploadedFiles(Arrays.asList(uploadfiles),request);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully uploaded - "+ uploadedFileName, HttpStatus.OK);
    }
//
//    // maps html form to a Model
//    @PostMapping("/api/upload/multi/model")
//    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model,HttpServletRequest request) {
//
//        logger.debug("Multiple file upload! With UploadModel");
//
//        try {
//
//            saveUploadedFiles(Arrays.asList(model.getFiles()),request);
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
//
//    }

    //save file
    private void saveUploadedFiles(List<MultipartFile> files,HttpServletRequest request) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String path1 = "";
//        try {
//            path1 = resourceLoader.getResource("file:").getFile().getAbsolutePath() + File.separator +"upload"+ File.separator ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //获取跟目录
        File test = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!test.exists()) test = new File("");
        System.out.println("path:"+test.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(test.getAbsolutePath(),"static/upload/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
//在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
//在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
        path1 = upload.getAbsolutePath() + "/";
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
//            String realPath = request.getServletContext().getRealPath(
//                    "//test//");
//            File root = new File(CommonUtils.IMAGE_DIR);
//            if (!root.exists()) {
//                //创建临时目录
//                root.mkdirs();
//                System.out.println( root.mkdir());
//            }
            Path path = Paths.get(path1 + file.getOriginalFilename());
//            File tempFile = new File(path1);
//            if (!tempFile.getParentFile().exists()) {
//                tempFile.getParentFile().mkdir();
//            }
//            if (!tempFile.exists()) {
//                try {
//                    tempFile.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            logger.info("{0}filesMsg:  "+JSON.toJSONString(file));
            DocContent docContent = new DocContent(bytes,file.getOriginalFilename());
//            docContentService.addDocContentMsg(docContent);
            Files.write(path, bytes);

            //将文件信息存入doc信息表中
//            String name, String content, String tags, int collectCnt, Date createTime, Date updateTime, int userId
            Doc doc = new Doc(file.getOriginalFilename(),"","tags",0,new Date(),new Date(),0,0,file.getName());
            docService.addDocMsg(doc);
            //将二进制流数据存入数据表中 id值 id 数字块 数据
        }

    }

    //文件下载相关代码
    @PostMapping("/download")
//    @RequestParam("fileName")
    public ResponseEntity<byte[]> download(@RequestBody String fileName,
                                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info(fileName);
        //        File file = new File("E://123.jpg");
//        byte[] body = null;
//        InputStream is = new FileInputStream(file);
//        body = new byte[is.available()];
//        is.read(body);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
//        HttpStatus statusCode = HttpStatus.OK;
//        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
try{
    String realPath = request.getServletContext().getRealPath(
            "//test//");
    CommonUtils.download(fileName,realPath,request,response);
} catch (Exception e){
    e.printStackTrace();
}
        return null;
    }


    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response){
        String fileName = "FileUploadTests.java";
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
        // 下载本地文件
        String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void downloadNet(HttpServletResponse response) throws MalformedURLException {
        // 下载网络文件
//        int bytesum = 0;
//        int byteread = 0;

//        URL url = new URL("windine.blogdriver.com/logo.gif");

//        try {
//            URLConnection conn = url.openConnection();
//            InputStream inStream = conn.getInputStream();
//            FileOutputStream fs = new FileOutputStream("c:/abc.gif");

//            byte[] buffer = new byte[1204];
//            int length;
//            while ((byteread = inStream.read(buffer)) != -1) {
//                bytesum += byteread;
//                 System.out.println(bytesum);
//                fs.write(buffer, 0, byteread);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    //Save the uploaded file to this folder
////    private static String UPLOADED_FOLDER = "D:/temp/";
//
//    //Single file upload
//    @PostMapping("/api/upload")
//    // If not @RestController, uncomment this
//    //@ResponseBody
//    public ResponseEntity<?> uploadFile(
//            @RequestParam("file") MultipartFile uploadfile) {
//
//        logger.debug("Single file upload!");
//
//        if (uploadfile.isEmpty()) {
//            return new ResponseEntity("please select a file!", HttpStatus.OK);
//        }
//
//        try {
//
//            saveUploadedFiles(Arrays.asList(uploadfile));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity("Successfully uploaded - " +
//                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
//
//    }

    public String upload(List<MultipartFile> files,HttpServletRequest request) {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String path = "";
        try {
            path = resourceLoader.getResource("file:").getFile().getAbsolutePath() + File.separator +"upload"+ File.separator ;

        } catch (IOException e) {
            e.printStackTrace();
        }

        ServletContext t = request.getSession().getServletContext();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(t);
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    path += new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(System.currentTimeMillis()) + "_" + file.getOriginalFilename();

                    File tempFile = new File(path);
                    if (!tempFile.getParentFile().exists()) {
                        tempFile.getParentFile().mkdir();
                    }
                    if (!tempFile.exists()) {
                        try {
                            tempFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        file.transferTo(tempFile);
                        logger.info(tempFile.getAbsolutePath());
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    logger.info("{0}filesMsg:  "+JSON.toJSONString(file));
                    //将文件信息存入doc信息表中
//            String name, String content, String tags, int collectCnt, Date createTime, Date updateTime, int userId
                    Doc doc = new Doc(file.getName(),"","tags",0,new Date(),new Date(),0,0,file.getName());
                    docService.addDocMsg(doc);
                    //将二进制流数据存入数据表中 id值 id 数字块 数据
                }

            }
        }
        return path;
    }

}