package com.example.backend.consumer.utils;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldManager;
import com.example.backend.physics.objs.GizmoWorldEdge;
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
        initWorld.add(new GizmoWorldEdge());
    }

    @Override
    public void run() {
        super.run();
        while(!isDone){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.logic();
            try {
                socketServer.sendMessage(world.getBall().toString());
                if(world.getLeftBaffle() != null) socketServer.sendMessage(world.getLeftBaffle().toString());
                if(world.getRightBaffle() != null) socketServer.sendMessage(world.getRightBaffle().toString());
            } catch (NullPointerException e){
                socketServer.endGame();
            }
        }
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
