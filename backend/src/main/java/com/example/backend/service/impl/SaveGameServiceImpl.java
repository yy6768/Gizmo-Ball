package com.example.backend.service.impl;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.service.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;

@Service
public class SaveGameServiceImpl implements SaveGameService {
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void saveFile(Date time, OutputStream stream) {

    }
}
