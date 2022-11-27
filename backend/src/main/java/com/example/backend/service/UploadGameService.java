package com.example.backend.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadGameService {
    Map<String,String> uploadGame(MultipartFile file) throws Exception;

}
