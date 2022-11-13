package com.example.backend.physics.testFiles;

import com.example.backend.physics.WorldManager;
import com.example.backend.physics.WorldPlace;
import com.example.backend.physics.objs.GizmoBall;
import com.example.backend.physics.objs.GizmoRectangle;
import com.example.backend.physics.objs.WorldObjects;
import com.example.backend.physicsInterface.GizmoObject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class MyKeyListener implements MouseListener{
    WorldManager worldManager;

    public MyKeyListener(WorldManager manager){
        this.worldManager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(MouseEvent.BUTTON1 == e.getButton()){
            rotateRect(e);
        }else if(MouseEvent.BUTTON2 == e.getButton()){
            magnifyRect(e);
        }
    }

    private void shrinkBall(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).shrink();
        }
    }

    private void magnifyBall(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).magnify();
        }
    }

    private void shrinkRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).shrink();
        }
    }

    private void magnifyRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).magnify();
        }
    }

    private void rotateRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).rotate();
        }
    }

    private void addBallToWorld(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        GizmoBall ball = new GizmoBall(WorldPlace.pixel2Mile(mx),WorldPlace.pixel2Height(my),2);
        worldManager.addToObjs(0, ball);
    }

    private void addWoodToWorld(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        GizmoRectangle polygon = new GizmoRectangle(WorldPlace.pixel2Mile(mx),WorldPlace.pixel2Height(my),5, 5);
        worldManager.addToObjs(0, polygon);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
