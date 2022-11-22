package com.example.backend.consumer.utils;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;

public class Game extends Thread {
    private final Integer rows;
    private final Integer cols;
    private final WorldManager world;
    private final WebSocketServer socketServer;
    private boolean isDone;

    public Game(WebSocketServer socket,WorldManager initWorld) {
        rows = 20;
        cols = 20;
        world = initWorld;
        isDone = false;
        socketServer = socket;
        initWorld.awakeAll();
    }

    @Override
    public void run() {
        super.run();
        while(!isDone){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.logic();
            world.getAllBalls().forEach(e-> System.out.println(e.toString()));
            world.getAllBalls().forEach(e-> socketServer.sendMessage(e.toString()));
        }
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
