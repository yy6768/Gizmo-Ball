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
 * 球类
 * 包括但不限于这个类，所以的组件类中的drawMe都是JPanel测试用类，只有我一个人会用到，其他人不用看
 */
public class GizmoBall extends WorldObjects {

    float r;
    //如果球的直径和轨道宽一致会导致球进不去轨道这个问题，因此实际设计上球的半径需要小一点
    Color color = Color.cyan;

    //在我的理解中，反射得到的构造函数是这个
    public GizmoBall(Integer id, Float x, Float y) {
        this.objectId = id;
        this.worldX = x + 0.5f;
        this.worldY = y - 0.5f;
        this.r = 0.45f;//为什么是0.45参考上面
        initBallInWorld();
    }

    public GizmoBall(float worldX, float worldY, float r) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.r = r;
        this.objectId = 0;
        initBallInWorld();
    }

    public int getPixelX() {
        int pixelX = WorldPlace.mile2Pixel(body.getPosition().x - r);
        return pixelX;
    }

    public int getPixelY() {
        int pixelY = WorldPlace.toPixelHeight(body.getPosition().y + r);
        return pixelY;
    }

    public int getDiameter() {
        return WorldPlace.mile2Pixel(r * 2);
    }

    private void initBallInWorld() {
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX, worldY);
        bd.type = BodyType.DYNAMIC;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = r;
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0f;
        fd.restitution = 0.99f;

        body = WorldPlace.world.createBody(bd);
        body.createFixture(fd);
    }

    @Override
    public void shrink() {
        if (isSizeLarge) {
            isSizeLarge = false;
            Shape bodyShape = body.getFixtureList().getShape();
            if (bodyShape.getType() == ShapeType.CIRCLE) {
                bodyShape.setRadius(1);
                body.getPosition().x -= 0.5f;
                body.getPosition().y += 0.5f;
                this.r = 0.45f;
            }

        }

        body.setAwake(true);
    }

    @Override
    public void magnify() {
        if (!isSizeLarge) {
            isSizeLarge = true;
            Shape bodyShape = body.getFixtureList().getShape();
            if (bodyShape.getType() == ShapeType.CIRCLE) {
                bodyShape.setRadius(WorldConstant.LENGTH);
                body.getPosition().x += 0.5f;
                body.getPosition().y -= 0.5f;
                this.r = 0.9f;
            }
        }
        body.setAwake(true);
    }

    @Override
    public String toString() {
        int size = isSizeLarge ? 2 : 1;
        float trueX = body.getPosition().x - (r + 0.05f) * size;
        float trueY = WorldConstant.CANVAS_HEIGHT - body.getPosition().y - (r + 0.05f) * size;
        float trueWorldX = worldX - (r + 0.05f) *  size;
        float trueWorldY = WorldConstant.CANVAS_HEIGHT - worldY - (r +0.05f) * size;
        return "Ball" + "#"
                + objectId + "#"
                + trueWorldX + "#"
                + trueWorldY + "#"
                + isSizeLarge + "#"
                + trueX + "#"
                + trueY;
    }

    //修改物体的物理学Type，非材质Type
    public void SetBallType(BodyType b_type) {
        body.m_type = b_type;
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(color);
        g.fillOval(this.getPixelX(), this.getPixelY(), this.getDiameter(), this.getDiameter());
    }

    public void reposition(){
        initBallInWorld();
    }
}
