package com.example.backend.physics.objs;

import com.example.backend.physics.WorldPlace;
import com.example.backend.physicsInterface.GizmoObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import java.awt.*;

/**
 * 抽象基类
 * 主要是为了方便在manager内调用
 * */

@Data
public abstract class WorldObjects implements GizmoObject {
    Integer objectId;
    Body body;
    Float worldX;
    Float worldY;

    boolean isSizeLarge = false;

    public abstract void drawMe(Graphics2D g);

    public ShapeType getShapeType(){
        if(body.m_fixtureList.m_shape == null)
            return null;

        return body.m_fixtureList.m_shape.m_type;
    }


    @Override
    public void rotate(){
        body.setTransform(body.getPosition(), (float) (body.getAngle() - Math.PI/2));
    }


}
