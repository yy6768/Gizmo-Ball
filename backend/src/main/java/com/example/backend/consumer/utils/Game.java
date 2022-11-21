package com.example.backend.consumer.utils;

import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;

public class Game extends Thread {
    private final Integer rows;
    private final Integer cols;
    private final WorldManager world;
    private boolean isDone;

    public Game(WorldManager initWorld) {
        rows = 20;
        cols = 20;
        world = initWorld;
        isDone = false;
        initWorld.awakeAll();
    }

    @Override
    public void run() {
        super.run();
        while(!isDone){
            for (GizmoObject objects: world.getAll()) {
                world.logic();
                System.out.println(objects.toString());
            }
        }
    }
}
