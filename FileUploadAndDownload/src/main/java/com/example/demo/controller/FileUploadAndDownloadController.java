package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
*@Author: Frank Luo
*@Date: 2021/8/31 16:36
*/
@Controller
public class FileUploadAndDownloadController {

    @RequestMapping("/")
    public String init(){
        return "index";
    }

    @RequestMapping("/index")
    public String initPage(){
        return "index";
    }

    @RequestMapping("/toSinglePage")
    public String toSingleFileUploadPage(){
        return "singleFileUpload";
    }

    @RequestMapping("/uploadSingleFile")
    public @ResponseBody String uploadSingleFile(@RequestParam("file") MultipartFile file){
        /*ref link: https://blog.csdn.net/qq_39516150/article/details/89367057 */

        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName = file.getOriginalFilename();
//            System.out.println("fileName: "+fileName);

            String uploadDir = "F:\\uploadDir\\";

            //if dir doesn't exist, create one
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }

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
}
