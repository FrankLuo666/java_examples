package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
