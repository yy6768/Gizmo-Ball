package com.example.backend.physics.objs;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldPlace;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

public class GizmoWorldEdge extends WorldObjects{

    float edge_width;
    float edge_height;

    public GizmoWorldEdge(){
        this.objectId = -1;
        this.worldX = 10f;
        this.worldY = 10f;
        this.edge_width = 10f;
        this.edge_height = 10f;
        initWorldEdgeInWorld();
    }

    public GizmoWorldEdge(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = 10f;
        this.worldY = 10f;
        this.edge_width = 10f;
        this.edge_height = 10f;
        initWorldEdgeInWorld();
    }

    private void initWorldEdgeInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;


        FixtureDef FD = new FixtureDef();
        FD.friction = 1f;
        ChainShape worldEdge = new ChainShape();
        Vec2[] vertices = {new Vec2(-edge_width, -edge_height), new Vec2(-edge_width, edge_height), new Vec2(edge_width, edge_height), new Vec2(edge_width, -edge_height)};
        worldEdge.createLoop(vertices, 4);
        FD.shape = worldEdge;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(FD);
    }

    @Override
    public String toString(){
        return "WorldEdge" + "#"
                + objectId + "#"
                + (body.getPosition().x - edge_width) + "#"
                + ((WorldConstant.HIGHT/WorldConstant.LENGTH - body.getPosition().y) - edge_height) + "#"
                + isSizeLarge;
    }





    @Override
    public void shrink(){
    }

    @Override
    public void magnify(){
    }


    private int getPixelX(){
        return WorldPlace.mile2Pixel(body.getPosition().x-edge_width);
    }
    private int getPixelY(){
        return WorldPlace.toPixelHeight(body.getPosition().y+edge_height);
    }
    private int getPixelWidth(){
        return WorldPlace.mile2Pixel(edge_width*2);
    }
    private int getPixelHeight(){
        return WorldPlace.mile2Pixel(edge_height*2);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(Color.PINK);
        g.drawRect(getPixelX(),getPixelY(),getPixelWidth(),getPixelHeight());
    }
}
