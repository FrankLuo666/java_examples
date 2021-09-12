package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
*@Author: Frank Luo
*@Date: 2021/8/31 16:36
* reference link: https://blog.csdn.net/qq_39516150/article/details/89367057
*/
@Controller
public class PageJumpController {

    @RequestMapping("/")
    public String init(){
        return "multipleFilesUpload";
    }

    @RequestMapping("/index")
    public String initPage(){
        return "index";
    }

    @RequestMapping("/toFileUploadPage")
    public String toFileUploadPage(){
        return "singleFileUpload";
    }

    @RequestMapping("/toFilesUploadPage")
    public String toFilesUploadPage(){
        return "multipleFilesUpload";
    }
}
