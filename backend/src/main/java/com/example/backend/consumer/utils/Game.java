package com.example.backend.consumer.utils;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldManager;
import com.example.backend.physics.objs.GizmoBaffle;
import com.example.backend.physics.objs.GizmoBall;
import com.example.backend.physics.objs.GizmoWorldEdge;
import com.example.backend.physicsInterface.GizmoObject;

public class Game extends Thread {
    private final WorldManager world;
    private final WebSocketServer socketServer;
    private boolean isDone;
    private GizmoBall ball;

    public Game(WebSocketServer socket, WorldManager initWorld) {
        world = initWorld;
        isDone = false;
        socketServer = socket;
        ball = (GizmoBall) initWorld.getBall();

    }

    @Override
    public void run() {
        super.run();
        while (!isDone) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            world.logic();
            StringBuilder builder = new StringBuilder();
            try {
                builder.append(world.getBall().toString()).append(" ");
                if (world.getLeftBaffle() != null) builder.append(world.getLeftBaffle().toString()).append(" ");
                if (world.getRightBaffle() != null) builder.append(world.getRightBaffle().toString()).append(" ");
                socketServer.sendMessage(builder.toString());
            } catch (NullPointerException e) {
                System.out.println(ball);
                world.add(ball);
                socketServer.endGame();
            }
        }
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setBaffle(String message) {
        String[] messages = message.split(" ");
        String type = messages[0];
        float direction = Integer.parseInt(messages[2]);
        GizmoBaffle baffle;
        if ("left".equalsIgnoreCase(type))
            baffle = (GizmoBaffle) world.getLeftBaffle();
        else
            baffle = (GizmoBaffle) world.getRightBaffle();
        baffle.setSpeed(direction * WorldConstant.BAFFLE_SPEED, 0f);
    }
}
