package com.example.backend.physics;

import com.example.backend.physics.objs.*;
import com.example.backend.physicsInterface.GizmoObject;
import com.example.backend.physicsInterface.GizmoWorld;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WorldManager implements GizmoWorld {

    List<WorldObjects> worldObjects = new ArrayList<>();

    public WorldManager() {
        initObjs();
    }

    private void initObjs() {

    //        GizmoBall ball = new GizmoBall(new Integer(1), new Float(12), new Float(20));
    //        worldObjects.add(ball);
    //
    //        GizmoRectangle wood1 = new GizmoRectangle(new Integer(3), new Float(18), new Float(10));
    //        worldObjects.add(wood1);
    //
    //        GizmoBaffle baf = new GizmoBaffle(new Integer(10), new Float(8), new Float(10));
    //        worldObjects.add(baf);
    //
    //        GizmoStraightPipe pipe = new GizmoStraightPipe(new Integer(10), new Float(12), new Float(10));
    //        worldObjects.add(pipe);
    //
    //        GizmoStraightPipe pipe1 = new GizmoStraightPipe(new Integer(10), new Float(11), new Float(9));
    //        pipe1.rotate();
    //        worldObjects.add(pipe1);
    //
    //        GizmoBendPipe bpipe = new GizmoBendPipe(new Integer(10), new Float(12), new Float(9));
    //        bpipe.rotate();
    //        worldObjects.add(bpipe);
    //
    //        GizmoBlackhole bhole = new GizmoBlackhole(new Integer(10), new Float(9), new Float(3));
    //        worldObjects.add(bhole);
    //
    //        GizmoWorldEdge edge = new GizmoWorldEdge();
    //        worldObjects.add(edge);
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

    //???????????????????????????????????????Null
    public GizmoObject getBall(){
        GizmoObject ball;

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBall) {
                ball = worldObjects.get(i);
                return ball;
            }
        }

        return null;
    }

    public GizmoObject getBlackHole(){
        GizmoObject BHole;

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBlackhole) {
                BHole = worldObjects.get(i);
                return BHole;
            }
        }

        return null;
    }

    public GizmoObject getLeftBaffle(){
        GizmoBaffle baffle;

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBaffle) {
                baffle = (GizmoBaffle) worldObjects.get(i);
                if(baffle.getIfBaffleSideIsLeft())
                    return baffle;
            }
        }

        return null;
    }

    public GizmoObject getRightBaffle(){
        GizmoBaffle baffle;

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBaffle) {
                baffle = (GizmoBaffle) worldObjects.get(i);
                if(!baffle.getIfBaffleSideIsLeft())
                    return baffle;
            }
        }

        return null;
    }

    @Override
    public GizmoObject get(Integer id) {
        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getObjectId() == id)
                return worldObjects.get(i);
        }

        //????????????????????????null
        return null;
    }

    //??????????????????????????????????????????????????????????????????????????????manager?????????????????????????????????
    //????????????????????????????????????????????????
    public void attractBallToBlackHole(Float attractDis, Float destroyDis){
        GizmoBlackhole bHole = (GizmoBlackhole) getBlackHole();
        GizmoBall ball = (GizmoBall)getBall();

        if(bHole == null || ball == null)
            return;

        Vec2 distanceVector = new Vec2(bHole.getBody().getPosition().x - ball.getBody().getPosition().x, bHole.getBody().getPosition().y - ball.getBody().getPosition().y);
        float distance = (float)Math.sqrt((double) (distanceVector.x * distanceVector.x + distanceVector.y * distanceVector.y));

        Vec2 normalizedVector = new Vec2(distanceVector.x/distance, distanceVector.y/distance);
        Vec2 force =  new Vec2(normalizedVector.x * bHole.power/distance * distance, normalizedVector.y * bHole.power/distance * distance);

        if(distance <= attractDis){
            ball.getBody().applyForce(force, ball.getBody().getPosition());
        }

        if(distance <= destroyDis){
            WorldPlace.world.destroyBody(((GizmoBall)getBall()).getBody());
            worldObjects.remove(getBall());
        }
    }


    //jbox2d?????????????????????????????????????????????????????????????????????????????????
    public void awakeAll() {
        for (int i = 0; i < worldObjects.size(); i++)
            worldObjects.get(i).getBody().setAwake(true);
    }

    //?????????????????????????????????
    public void clearWorld(){
        worldObjects.clear();
    }








    //????????????????????????JPanel????????????, ?????????????????????

    public List<GizmoBaffle> getLBaffle_Test(){
        List<GizmoBaffle> baffles = new ArrayList<GizmoBaffle>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBaffle) {
                GizmoBaffle baffle = (GizmoBaffle) worldObjects.get(i);
                if(baffle.getIfBaffleSideIsLeft())
                    baffles.add(baffle);
            }
        }

        return baffles;
    }

    public List<WorldObjects> getRBaffle_Test(){
        List<WorldObjects> baffles = new ArrayList<WorldObjects>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i) instanceof GizmoBaffle) {
                GizmoBaffle baffle = (GizmoBaffle) worldObjects.get(i);
                if(!baffle.getIfBaffleSideIsLeft())
                    baffles.add(baffle);
            }
        }

        return baffles;
    }

    public List<WorldObjects> getAllBalls_Test() {
        List<WorldObjects> balls = new ArrayList<WorldObjects>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getShapeType() == ShapeType.CIRCLE) {
                balls.add(worldObjects.get(i));
            }
        }
        return balls;
    }

    public List<WorldObjects> getAllRects_Test() {
        List<WorldObjects> balls = new ArrayList<WorldObjects>();

        for (int i = 0; i < worldObjects.size(); i++) {
            if (worldObjects.get(i).getShapeType() == ShapeType.POLYGON
                    || (worldObjects.get(i) instanceof GizmoCircle)
                    || worldObjects.get(i) instanceof GizmoStraightPipe ) {
                balls.add(worldObjects.get(i));
            }
        }
        return balls;
    }


    public void logic() {
        WorldPlace.step();
        attractBallToBlackHole(3f,0.8f);
//        if(getBall()!=null)
//            System.out.println(getBall().toString());
    }

    public void draw(Graphics2D g) {
        worldObjects.forEach(it -> it.drawMe(g));
    }

    public void addToObjs(Integer id, WorldObjects obj) {
        this.worldObjects.add(obj);
    }

}
