package com.example.backend.physics.objs;

import com.example.backend.physics.WorldPlace;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * 正方形类
 * 包括但不限于这个类，所以的组件类中的drawMe都是JPanel测试用类，只有我一个人会用到，其他人不用看
 * */
public class GizmoRectangle extends WorldObjects{
    float block_width;
    float block_height;

    //在我的理解中，反射得到的构造函数是这个
    public GizmoRectangle(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = x;
        this.worldY = y;
        this.block_width = 1;
        this.block_height = 1;
        initRectangleInWorld(worldX,worldY,block_width,block_height);
    }

    public GizmoRectangle(float worldX, float worldY, float hw, float hh){
        this.objectId = 0;
        initRectangleInWorld(worldX,worldY,hw,hh);
    }

    private void initRectangleInWorld(float worldX,float worldY,float hw,float hh){
        this.block_width = hw;
        this.block_height = hh;
        BodyDef bd = new BodyDef();

        this.worldX = worldX;
        this.worldY = worldY;

        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;

        FixtureDef FD = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(block_width,block_height);

        FD.shape = polygon;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(FD);

    }

    @Override
    public void shrink(){
        if(isSizeLarge){
            isSizeLarge = false;
            Shape bodyShape = body.getFixtureList().getShape();
            if(bodyShape.getType() == ShapeType.POLYGON){
                this.block_width = 2;
                this.block_height = 2;
                FixtureDef FD = new FixtureDef();
                PolygonShape polygon = new PolygonShape();
                polygon.setAsBox(block_width,block_height);
                FD.shape = polygon;
                body.destroyFixture(body.getFixtureList());
                body.createFixture(FD);
            }

        }

        body.setAwake(true);
    }

    @Override
    public void magnify(){
        if(!isSizeLarge){
            isSizeLarge = true;
            Shape bodyShape = body.getFixtureList().getShape();
            if(bodyShape.getType() == ShapeType.POLYGON){
                this.block_width = 4;
                this.block_height = 4;
                FixtureDef FD = new FixtureDef();
                PolygonShape polygon = new PolygonShape();
                polygon.setAsBox(block_width,block_height);
                FD.shape = polygon;
                body.destroyFixture(body.getFixtureList());
                body.createFixture(FD);
            }
        }

        body.setAwake(true);
    }

    //这里返回的是正方体的中心坐标点
    @Override
    public String toString(){
        return "Rectangle" + "#"
                +"{" + objectId + "}" + "#"
                +"{" + worldX + "}" + "#"
                +"{" + worldY + "}" + "#"
                +"{" + isSizeLarge + "}";

    }


    private int getPixelX(){
        return WorldPlace.mile2Pixel(body.getPosition().x-block_width);
    }
    private int getPixelY(){
        return WorldPlace.toPixelHeight(body.getPosition().y+block_height);
    }
    private int getPixelWidth(){
        return WorldPlace.mile2Pixel(block_width*2);
    }
    private int getPixelHeight(){
        return WorldPlace.mile2Pixel(block_height*2);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(Color.PINK);
        g.fillRect(getPixelX(),getPixelY(),getPixelWidth(),getPixelHeight());
    }
}
