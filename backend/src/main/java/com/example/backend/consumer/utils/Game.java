package com.example.backend.consumer.utils;

import com.example.backend.physicsInterface.GizmoWorld;

public class Game extends Thread {
    private final Integer rows;
    private final Integer cols;
    private final GizmoWorld world;

    public Game(GizmoWorld initWorld) {
        rows = 20;
        cols = 20;
        world = initWorld;
    }
}
