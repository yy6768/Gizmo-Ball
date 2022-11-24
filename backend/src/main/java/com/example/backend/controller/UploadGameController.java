package com.example.backend.controller;


import com.example.backend.service.UploadGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
public class UploadGameController {
    @Autowired
    private UploadGameService uploadGameService;

    @PostMapping("/upload")
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        return uploadGameService.uploadGame(file);
    }
}
