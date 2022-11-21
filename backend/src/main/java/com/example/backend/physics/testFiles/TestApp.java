package com.example.backend.physics.testFiles;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldManager;
import com.example.backend.physics.WorldPlace;

import javax.swing.*;


public class TestApp {
    public static void main(String[] args) {
        WorldManager worldManager = new WorldManager();

        JFrame frame = new JFrame("box1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WorldConstant.WIDTH,WorldConstant.HIGHT);

        MyPanel myPanel = new MyPanel();
        myPanel.setManager(worldManager);

        frame.add(myPanel);
        frame.setVisible(true);
        myPanel.addMouseListener(new MyKeyListener(worldManager));
        new Thread(myPanel).start();
    }
}
