package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.util.UUID;

/**
*@Author: Frank Luo
*@Date: 2021/9/12 12:09
* reference link: https://blog.csdn.net/qq_39516150/article/details/89367057
*/
@Controller
public class FileUploadController {

    @Value("${fileUploadDir_Windows}")
    private String fileUploadDir_Windows;

    @Value("${fileUploadDir_Linux}")
    private String fileUploadDir_Linux;

    @RequestMapping("/uploadSingleFile")
    public @ResponseBody String uploadSingleFile(@RequestParam("file") MultipartFile file){

        try {
            // set upload directory
            String uploadDir;
            if("Windows".equals(judgeSystem())){
                uploadDir = fileUploadDir_Windows;
            }else{
                uploadDir = fileUploadDir_Linux;
            }

            //if dir doesn't exist, create one
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }

            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName = file.getOriginalFilename();
//            System.out.println("fileName: "+fileName);
//            String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            System.out.println("fileSuffix : "+fileSuffix);

            //add UUID to file name and upload file
            File serverFile = new File(uploadDir+uuid+"-"+fileName);
//            System.out.println("serverFile: "+serverFile);
            file.transferTo(serverFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "upload failed!";
        }

        return "uploaded successfully!";
    }

    @RequestMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(@RequestParam("file") MultipartFile[] files){

        try {
            // set upload directory
            String uploadDir;
            if("Windows".equals(judgeSystem())){
                uploadDir = fileUploadDir_Windows;
            }else{
                uploadDir = fileUploadDir_Linux;
            }

            //if dir doesn't exist, create one
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }

            for(int i=0;i<files.length;i++){
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                String fileName = files[i].getOriginalFilename();
                if(!StringUtils.isEmpty(fileName)){
                    //add UUID to file name and upload file
                    File serverFile = new File(uploadDir+uuid+"-"+fileName);
                    files[i].transferTo(serverFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "upload failed!";
        }

        return "uploaded successfully!";
    }

    /**
     * judge the OS System
     *
     * @return Os System
     */
    public String judgeSystem(){
//        System.getProperties().list(System.out);
        String OS = System.getProperty("os.name");
        System.out.println(OS);
        String OS_return = null;
        if(OS.startsWith("Windows")){
            OS_return = "Windows";
        }else{
            OS_return = "Linux";
        }
        return OS_return;
    }
}
