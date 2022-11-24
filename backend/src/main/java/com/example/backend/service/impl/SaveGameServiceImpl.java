package com.example.backend.service.impl;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldManager;
import com.example.backend.service.SaveGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@Service
public class SaveGameServiceImpl implements SaveGameService {
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void saveFile(OutputStream stream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        WorldManager worldManager = webSocketServer.getWorldManager();
        writer.write(worldManager.toString());
        writer.flush();
    }
}
