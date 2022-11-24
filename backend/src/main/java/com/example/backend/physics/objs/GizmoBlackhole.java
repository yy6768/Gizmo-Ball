package com.example.backend.physics.objs;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldPlace;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;

public class GizmoBlackhole extends WorldObjects{

    float r;
    public float power;

    public GizmoBlackhole(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = x + 0.5f;
        this.worldY = y - 0.5f;
        this.r = 0.5f;
        this.power = 100f;
        initBHoleInWorld();
    }

    private void initBHoleInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;
        body = WorldPlace.world.createBody(bd);
    }


    @Override
    public void shrink(){
    }

    @Override
    public void magnify(){
    }

    @Override
    public String toString(){
        return "BlackHole" + "#"
                + objectId + "#"
                + (body.getPosition().x - r) + "#"
                + ((WorldConstant.HIGHT/WorldConstant.LENGTH - body.getPosition().y) - r);
    }




    public int getPixelX(){
        int pixelX = WorldPlace.mile2Pixel(body.getPosition().x-r);
        return pixelX;
    }

    public int getPixelY(){
        int pixelY = WorldPlace.toPixelHeight(body.getPosition().y+r);
        return pixelY;
    }

    public int getDiameter(){
        return WorldPlace.mile2Pixel(r*2);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.fillOval(this.getPixelX(),this.getPixelY(),this.getDiameter(),this.getDiameter());
    }
}
