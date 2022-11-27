package com.example.backend.physics.objs;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldPlace;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.EdgeShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

public class GizmoStraightPipe extends WorldObjects{

    float rail_width;
    float rail_height;
    boolean ifVertical = true;

    public GizmoStraightPipe(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = x + 0.5f;
        this.worldY = y - 0.5f;
        this.rail_width = 0.5f;
        this.rail_height = 0.5f;
        initStraightPipeInWorld();
    }

    private void initStraightPipeInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;
        body = WorldPlace.world.createBody(bd);


        FixtureDef FDL = new FixtureDef();
        FixtureDef FDR = new FixtureDef();
        FDL.friction = 1f;
        FDR.friction = 1f;
        EdgeShape PipeL = new EdgeShape();
        EdgeShape PipeR = new EdgeShape();
        Vec2[] vertices = {new Vec2(-rail_width, -rail_height), new Vec2(-rail_width, rail_height)};
        Vec2[] vertices2 = {new Vec2(rail_width, -rail_height), new Vec2(rail_width, rail_height)};
        PipeL.set(new Vec2(-rail_width, -rail_height), new Vec2(-rail_width, rail_height));
        PipeR.set(new Vec2(rail_width, -rail_height), new Vec2(rail_width, rail_height));


        FDL.shape = PipeL;
        FDR.shape = PipeR;

        body.createFixture(FDL);
        body.createFixture(FDR);
    }


    @Override
    public void shrink(){
    }

    @Override
    public void magnify(){
    }

    @Override
    public void rotate(){
        body.setTransform(body.getPosition(), (float) (body.getAngle() - Math.PI/2));
        rotateStatusPass();
    }

    //为竖直时返回1，水平返回0
    @Override
    public String toString() {
        return "StraightPipe" + "#"
                + objectId + "#"
                + (body.getPosition().x - rail_width) + "#"
                + ((WorldConstant.HIGHT/WorldConstant.LENGTH - body.getPosition().y) - rail_height) + "#"
                + (ifVertical?0:1);
    }

    private void rotateStatusPass(){
        ifVertical = !ifVertical;
    }



    //之后均为测试用代码
    private int getPixelX(){
        return WorldPlace.mile2Pixel(body.getPosition().x - rail_width);
    }
    private int getPixelY(){
        return WorldPlace.toPixelHeight(body.getPosition().y + rail_height);
    }
    private int getPixelWidth(){
        return WorldPlace.mile2Pixel(rail_width *2);
    }
    private int getPixelHeight(){
        return WorldPlace.mile2Pixel(rail_height *2);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(Color.PINK);
        if(ifVertical){
            g.drawLine(getPixelX(), getPixelY(), getPixelX(),getPixelY()+getPixelHeight());
            g.drawLine(getPixelX() + getPixelWidth(), getPixelY(), getPixelX() + getPixelWidth(),getPixelY()+getPixelHeight());
        }
        else{
            g.drawLine(getPixelX(), getPixelY(), getPixelX() + getPixelWidth(),getPixelY());
            g.drawLine(getPixelX(), getPixelY()+getPixelHeight(), getPixelX() + getPixelWidth(),getPixelY()+getPixelHeight());
        }
    }
}
