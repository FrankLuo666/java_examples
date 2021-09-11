package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.CsvService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Frank Luo
 */
@Slf4j
@Controller
public class CsvDownloadController {
    UserService userService = new UserServiceImpl();
    CsvService csvService = new CsvService();

    @ResponseBody
    @RequestMapping(value = "/csvDownloadAjax", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Resource> csvDownload(HttpServletResponse res) {
        log.info("Downloading users csv");
        List<User> userList = userService.getUserList();
        final InputStreamResource resource = new InputStreamResource(this.csvService.load(userList));
        String header = "attachment;filename=users.csv";
        String contentType = "application/csv";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, header)
                .contentType(MediaType.parseMediaType(contentType)).body(resource);
    }

}
