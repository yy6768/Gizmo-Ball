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

public class GizmoBendPipe extends WorldObjects{

    float rail_width;
    float rail_height;
    Integer railRotateStatus = 0;

    public GizmoBendPipe(Integer id, Float x, Float y){
        this.objectId = id;
        this.worldX = x + 0.5f;
        this.worldY = y - 0.5f;
        this.rail_width = 0.5f;
        this.rail_height = 0.5f;
        initBendPipeInWorld();
    }

    private void initBendPipeInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;
        body = WorldPlace.world.createBody(bd);


        FixtureDef FD = new FixtureDef();
        FD.friction = 0f;
        ChainShape Pipe = new ChainShape();
        Vec2[] vertices = {new Vec2(-rail_width, rail_height), new Vec2(rail_width * 0.3f, rail_height)
        ,new Vec2(rail_width, rail_height * 0.3f), new Vec2(rail_width, -rail_height)};
        Pipe.createChain(vertices, 4);

        FD.shape = Pipe;

        body.createFixture(FD);
    }

    private void initBendPipeInWorld_MoreVertex(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.STATIC;
        body = WorldPlace.world.createBody(bd);


        FixtureDef FD = new FixtureDef();
        FD.friction = 0f;
        ChainShape Pipe = new ChainShape();
        Vec2[] vertices = {new Vec2(-rail_width, rail_height), new Vec2(rail_width * 0.9f, rail_height)
                ,new Vec2(rail_width, rail_height * 0.9f), new Vec2(rail_width, -rail_height)};
        Pipe.createChain(vertices, 4);

        FD.shape = Pipe;

        body.createFixture(FD);
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

    private void rotateStatusPass(){
        railRotateStatus++;
        if(railRotateStatus == 4)
            railRotateStatus = 0;

    }

    @Override
    public String toString() {
        float initX = (body.getPosition().x - rail_width);
        float initY = ((WorldConstant.CANVAS_HEIGHT - body.getPosition().y) - rail_height);
        return "BendPipe" + "#"
                + objectId + "#"
                + initX + "#"
                + initY + "#"
                + railRotateStatus;
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
        g.drawRect(getPixelX(),getPixelY(),getPixelWidth(),getPixelHeight());
    }
}
