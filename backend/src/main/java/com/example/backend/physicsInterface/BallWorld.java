package com.example.backend.physicsInterface;

import java.util.List;

public interface BallWorld {
    void add(BallObject e);
    void delete(BallObject e);
    List<BallObject> getAll();
}
