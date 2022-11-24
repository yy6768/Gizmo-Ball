package com.example.backend.physics.objs;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldPlace;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * 固定圆类
 * 包括但不限于这个类，所以的组件类中的drawMe都是JPanel测试用类，只有我一个人会用到，其他人不用看
 * */

public class GizmoCircle extends WorldObjects{

    float r;
    Color color = Color.PINK;

    //在我的理解中，反射得到的构造函数是这个
    public GizmoCircle(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = x + 0.5f;
        this.worldY = y - 0.5f;
        this.r = 0.5f;
        initBallInWorld();
    }


    private void initBallInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = r;
        fd.shape = cs;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(fd);
    }

    @Override
    public void shrink(){
        if(isSizeLarge){
            isSizeLarge = false;
            Shape bodyShape = body.getFixtureList().getShape();
            if(bodyShape.getType() == ShapeType.CIRCLE){

                this.r = 0.5f;
                body.getPosition().x -= 0.5f;
                body.getPosition().y += 0.5f;

                FixtureDef fd = new FixtureDef();
                CircleShape cs = new CircleShape();
                cs.m_radius = r;
                fd.shape = cs;

                body.destroyFixture(body.getFixtureList());
                body.createFixture(fd);
            }

        }

        body.setAwake(true);
    }

    @Override
    public void magnify(){
        if(!isSizeLarge){
            isSizeLarge = true;
            Shape bodyShape = body.getFixtureList().getShape();
            if(bodyShape.getType() == ShapeType.CIRCLE){

                this.r = 1;
                body.getPosition().x += 0.5f;
                body.getPosition().y -= 0.5f;

                FixtureDef fd = new FixtureDef();
                CircleShape cs = new CircleShape();
                cs.m_radius = r;
                fd.shape = cs;

                body.destroyFixture(body.getFixtureList());
                body.createFixture(fd);
            }
        }

        body.setAwake(true);
    }

    @Override
    public String toString(){
        return "Circle" + "#"
                + objectId + "#"
                + (body.getPosition().x - r) + "#"
                + ((WorldConstant.HIGHT/WorldConstant.LENGTH - body.getPosition().y) - r) + "#"
                + isSizeLarge;
    }

    //修改物体的物理学Type，非材质Type
    public void SetBallType(BodyType b_type){
        body.m_type = b_type;
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
        g.setColor(color);
        g.fillOval(this.getPixelX(),this.getPixelY(),this.getDiameter(),this.getDiameter());
    }
}
