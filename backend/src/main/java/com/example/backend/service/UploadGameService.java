package com.example.backend.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UploadGameService {
    JSONObject uploadGame(MultipartFile file) throws Exception;

}
