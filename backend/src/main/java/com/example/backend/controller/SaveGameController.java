package com.example.backend.controller;

import com.example.backend.service.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SaveGameController {

    @Autowired
    private SaveGameService service;

    @PostMapping("/save")
    public void saveFile(@RequestParam  final HttpServletResponse response){
        OutputStream os;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
            String filename = "GizmoText" + format.format(date);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("date".getBytes("utf-8"), "iso-8859-1"));
            service.saveFile(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
