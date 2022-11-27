package com.example.backend.physics.objs;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldPlace;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

public class GizmoBaffle extends WorldObjects{

    float Baffle_width;
    float Baffle_height;
    Boolean ifLeft;

    public GizmoBaffle(Integer id, Float x, Float y) {
        ifLeft = x <= 10.0;
        this.objectId = id;
        this.worldX = x + 1f;
        this.worldY = y - 0.1f;
        this.Baffle_width = 1f;
        this.Baffle_height = 0.1f;
        initBaffleInWorld_DYNAMIC();
    }

    public void setAsRightBaffle(){
        ifLeft = false;
    }

    public boolean getIfBaffleSideIsLeft(){
        return ifLeft;
    }

    private void initBaffleInWorld_KINEMATIC(){
        BodyDef bd = new BodyDef();

        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.KINEMATIC;

        FixtureDef FD = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(Baffle_width,Baffle_height);

        FD.shape = polygon;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(FD);
    }

    private void initBaffleInWorld_DYNAMIC(){
        BodyDef bd = new BodyDef();

        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.DYNAMIC;
        bd.gravityScale = 0f;

        FixtureDef FD = new FixtureDef();
        PolygonShape polygon = new PolygonShape();

        polygon.setAsBox(Baffle_width,Baffle_height);
        FD.shape = polygon;
        FD.friction = 0;
        FD.density = 200000000f;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(FD);
    }

    public void setSpeed(Float x, Float y){
        body.setLinearVelocity(new Vec2(x,y));
    }

    public void stopBaffle(){
        body.setLinearVelocity(new Vec2(0, 0));
    }

    public void reposition(){
        initBaffleInWorld_DYNAMIC();
    }


    @Override
    public void shrink(){
        if (isSizeLarge) {
            isSizeLarge = false;
            Shape bodyShape = body.getFixtureList().getShape();
            this.Baffle_width = 1f;
            this.Baffle_height = 0.1f;

            body.getPosition().x -= 1f;
            FixtureDef FD = new FixtureDef();
            PolygonShape polygon = new PolygonShape();
            polygon.setAsBox(Baffle_width,Baffle_height);
            FD.shape = polygon;
            body.destroyFixture(body.getFixtureList());
            body.createFixture(FD);

        }

        body.setAwake(true);
    }

    @Override
    public void magnify(){
        if(!isSizeLarge){
            isSizeLarge = true;
            Shape bodyShape = body.getFixtureList().getShape();
            if(bodyShape.getType() == ShapeType.POLYGON){
                this.Baffle_width = 2f;
                this.Baffle_height = 0.1f;
                body.getPosition().x += 1f;
                FixtureDef FD = new FixtureDef();
                PolygonShape polygon = new PolygonShape();
                polygon.setAsBox(Baffle_width,Baffle_height);
                FD.shape = polygon;
                body.destroyFixture(body.getFixtureList());
                body.createFixture(FD);
            }
        }

        body.setAwake(true);
    }

    @Override
    public String toString() {
        float trueX = body.getPosition().x - Baffle_width;
        float trueY = (WorldConstant.CANVAS_HEIGHT - body.getPosition().y) - Baffle_height;
        float initX = worldX - Baffle_width;
        float initY = (WorldConstant.CANVAS_HEIGHT - worldY) - Baffle_height;
        return "Baffle" + "#"
                + objectId + "#"
                + initX + "#"
                + initY + "#"
                + ifLeft + "#"
                + trueX + "#"
                + trueY;
    }



    private int getPixelX(){
        return WorldPlace.mile2Pixel(body.getPosition().x-Baffle_width);
    }
    private int getPixelY(){
        return WorldPlace.toPixelHeight(body.getPosition().y+Baffle_height);
    }
    private int getPixelWidth(){
        return WorldPlace.mile2Pixel(Baffle_width*2);
    }
    private int getPixelHeight(){
        return WorldPlace.mile2Pixel(Baffle_height*2);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(Color.PINK);
        g.drawRect(getPixelX(),getPixelY(),getPixelWidth(),getPixelHeight());
    }
}
