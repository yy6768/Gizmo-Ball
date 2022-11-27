package com.example.backend.controller;

import com.example.backend.service.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
public class SaveGameController {

    @Autowired
    private SaveGameService service;

    @GetMapping("/download")
    public void saveFile(HttpServletResponse response) {
        OutputStream os;
        try {
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            // 取得输出流
            os = response.getOutputStream();
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
            String filename = "GizmoText" + format.format(date) + ".txt";
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "iso-8859-1"));
            service.saveFile(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
