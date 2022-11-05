package com.example.backend.consumer.utils;

import com.example.backend.physicsInterface.BallWorld;

public class Game extends Thread {
    private final Integer rows;
    private final Integer cols;
    private final BallWorld world;

    public Game(BallWorld initWorld) {
        rows = 20;
        cols = 20;
        world = initWorld;
    }
}
