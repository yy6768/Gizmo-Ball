package com.example.backend.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.example.backend.physics.WorldConstant;

// Pixel对应JPanel,引入JPanel是为了方便测试
// 实际使用中Mile才是物理引擎中的单位
// 此类中成员都是静态成员，主要是用于给其他类调用
// 使用于Rectangle,Triangle里的getPixel函数得到的是模型的左上角坐标, 默认调用得到的是模型的中间坐标

public class WorldPlace {
    public static World world = new World(new Vec2(0f,-10f));


    public static int mile2Pixel(float mile){
        return (int)(mile*WorldConstant.LENGTH);
    }

    public static float pixel2Mile(int pixel){
        return pixel/WorldConstant.LENGTH;
    }

    public static int toPixelHeight(float mile){
        return WorldConstant.HIGHT - (int) (mile*WorldConstant.LENGTH);
    }

    public static float pixel2Height(int height){
        return (WorldConstant.HIGHT - height)/WorldConstant.LENGTH;
    }

    public static void step(){
            world.step(WorldConstant.TIME_STEP,6,6);
    }

}
