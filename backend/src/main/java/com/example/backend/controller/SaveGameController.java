package com.example.backend.controller;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveGameController {
    @PostMapping("/save/file")
    public void saveFile(@RequestParam MultiValueMap<String,String> data){

    }
}
