package com.example.backend.physics.testFiles;

import com.example.backend.physics.WorldManager;
import com.example.backend.physics.WorldConstant;

import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel implements Runnable{
    WorldManager manager;

    public void setManager(WorldManager manager) {
        this.manager = manager;
    }

    public MyPanel(){
        this.setSize(WorldConstant.WIDTH,WorldConstant.HIGHT);
        this.setVisible(true);
        this.setBackground(Color.black);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        manager.draw((Graphics2D)g);
        g.dispose();
    }

    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(30);
                this.repaint();
                manager.logic();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
