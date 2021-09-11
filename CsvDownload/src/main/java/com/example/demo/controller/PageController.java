package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = "/csvDownloadPage")
    public String showsvDownloadCPage() {
        return "csvDownload";
    }
}
