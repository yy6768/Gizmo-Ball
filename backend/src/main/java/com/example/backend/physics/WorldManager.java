package com.example.backend.physics;

import com.example.backend.physics.objs.*;
import com.example.backend.physicsInterface.GizmoObject;
import com.example.backend.physicsInterface.GizmoWorld;
import org.jbox2d.collision.shapes.ShapeType;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WorldManager implements GizmoWorld {

    List<WorldObjects> worldObjects = new ArrayList<>();

    public WorldManager() {
        initObjs();
    }

    private void initObjs() {
        //GizmoBall ball = new GizmoBall(20f,40f,1f);
        //worldObjects.add(ball) ;

        //GizmoRectangle wood1 = new GizmoRectangle(20f,20f,5f,5f);
        //worldObjects.add(wood1);

        //GizmoTriangle tri = new GizmoTriangle(27f,20f,10f,10f);
        //worldObjects.add(tri);

        //GizmoCircle sph = new GizmoCircle(18f,20f,8f);
        //worldObjects.add(sph);

        //GizmoCircle sph1 = new GizmoCircle(22f,20f,8f);
        //worldObjects.add(sph1);
    }


    @Override
    public void add(GizmoObject e) {
        worldObjects.add((WorldObjects) e);
    }

    @Override
    public void delete(Integer dlt_id) {
        WorldObjects dltObject = worldObjects.stream().filter(p -> Objects.equals(p.getObjectId(), dlt_id)).findAny().orElse(null);
        if (dltObject != null) {
            worldObjects.remove(dltObject);
        }
    }

    @Override
    public List<GizmoObject> getAll() {
        List<GizmoObject> allList = new ArrayList<GizmoObject>();

        for (int i = 0; i < worldObjects.size(); i++) {
            allList.add(worldObjects.get(i));
        }

        return allList;

    }

    @Override
    public GizmoObject get(Integer id) {
        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getObjectId() == id)
                return worldObjects.get(i);
        }

        //如果找不到就返回null
        return null;
    }


    //jbox2d已经静止的物体若没有遇到碰撞是会处于静态的需要手动唤醒
    public void awakeAll() {
        for (int i = 0; i < worldObjects.size(); i++)
            worldObjects.get(i).getBody().setAwake(true);
    }


    //在这之后的全部为JPanel测试代码

    public List<WorldObjects> getAllBalls() {
        List<WorldObjects> balls = new ArrayList<WorldObjects>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getShapeType() == ShapeType.CIRCLE) {
                balls.add(worldObjects.get(i));
            }
        }
        return balls;
    }

    public List<WorldObjects> getAllRects() {
        List<WorldObjects> balls = new ArrayList<WorldObjects>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getShapeType() == ShapeType.POLYGON) {
                balls.add(worldObjects.get(i));
            }
        }
        return balls;
    }


    public void logic() {
        WorldPlace.step();
    }

    public void draw(Graphics2D g) {
        worldObjects.forEach(it -> it.drawMe(g));
    }

    public void addToObjs(Integer id, WorldObjects obj) {
        this.worldObjects.add(obj);
    }

}
