package com.example.backend.physics.testFiles;

import com.example.backend.physics.WorldManager;
import com.example.backend.physics.WorldPlace;
import com.example.backend.physics.objs.GizmoBaffle;
import com.example.backend.physics.objs.GizmoBall;
import com.example.backend.physics.objs.GizmoRectangle;
import com.example.backend.physics.objs.WorldObjects;
import com.example.backend.physicsInterface.GizmoObject;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MyKeyListener implements MouseListener{
    WorldManager worldManager;

    public MyKeyListener(WorldManager manager){
        this.worldManager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(MouseEvent.BUTTON1 == e.getButton()){
            movebaffleL(e);
        }else if(MouseEvent.BUTTON2 == e.getButton()){
            rotateRect(e);
        }
        else if(MouseEvent.BUTTON3 == e.getButton()) {
            movebaffleR(e);
        }
    }

    private void movebaffleL(MouseEvent e){
        List<GizmoObject> baffles = new ArrayList<GizmoObject>();
        baffles.add(worldManager.getLeftBaffle());

        for(int i = 0;i<baffles.size();i++){
             GizmoBaffle baffle = (GizmoBaffle)baffles.get(i);
             baffle.setSpeed(6f, 0f);
        }
    }

    private void movebaffleR(MouseEvent e){
        List<GizmoBaffle> baffles = worldManager.getLBaffle_Test();

        for(int i = 0;i<baffles.size();i++){
            baffles.get(i).setSpeed(-6f, 0f);
        }
    }

    private void stopBaffle(MouseEvent e){
        List<GizmoBaffle> baffles = worldManager.getLBaffle_Test();

        for(int i = 0;i<baffles.size();i++){
            baffles.get(i).stopBaffle();
        }
    }

    private void moveballR(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).getBody().applyForce(new Vec2(200f,0f), balls.get(i).getBody().getPosition());
        }
    }

    private void moveballL(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).getBody().applyForce(new Vec2(-200f,0f), balls.get(i).getBody().getPosition());
        }
    }

    private void moveballU(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).getBody().applyForce(new Vec2(0,100f), balls.get(i).getBody().getPosition());
        }
    }

    private void shrinkBall(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).shrink();
        }
    }

    private void magnifyBall(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllBalls_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).magnify();
        }
    }

    private void shrinkRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).shrink();
        }
    }

    private void magnifyRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects_Test();

        for(int i = 0;i<balls.size();i++){
            balls.get(i).magnify();
        }
    }

    private void rotateRect(MouseEvent e){
        List<WorldObjects> balls = worldManager.getAllRects_Test();

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
