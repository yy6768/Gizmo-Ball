package com.example.backend.service.impl;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;
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
    private WorldManager worldManager;

    @Override
    public void saveFile(OutputStream stream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        StringBuilder builder = new StringBuilder();
        for (GizmoObject e: worldManager.getAll()) {
            if (e.toString().startsWith("WorldEdge")) continue;
            builder.append(e).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }
}
