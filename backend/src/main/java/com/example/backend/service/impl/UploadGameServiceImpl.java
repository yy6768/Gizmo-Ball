package com.example.backend.service.impl;

import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;
import com.example.backend.service.UploadGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadGameServiceImpl implements UploadGameService {
    @Autowired
    private WorldManager worldManager;
    @Override
    public Map<String,String> uploadGame(MultipartFile file) throws Exception{
        Map<String,String> result = new HashMap<>();
        InputStream is = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder infos = new StringBuilder();
        worldManager.clearWorld();
        while((line = reader.readLine()) != null){
            if(line.length() == 0) continue;
            boolean res = addComponents(line);
            if(!res){
                result.put("result","fail");
                worldManager.clearWorld();
                return result;
            }
            infos.append(line).append(" ");
        }
        result.put("result","success");
        result.put("infos",infos.toString());

        return result;
    }

    private boolean addComponents(String message) {
        String[] messages = message.split("#");
        int length = messages.length;
        try {
            //类型
            String type = messages[0];
            //反射动态创建组件
            int id = Integer.parseInt(messages[1]);
            //组件的横坐标
            float x = Float.parseFloat(messages[2]);
            //组件的纵坐标，因为物理引擎和画布上下颠倒，所以需要使用画布高度减当前的坐标
            float y = WorldConstant.CANVAS_HEIGHT - Float.parseFloat(messages[3]);
            Class<?> objectType;
            objectType = Class.forName("com.example.backend.physics.objs.Gizmo" + type);
            Constructor<?> constructor = objectType.getConstructor(Integer.class, Float.class, Float.class);
            GizmoObject object = (GizmoObject) constructor.newInstance(id, x, y);
            if("Baffle".equalsIgnoreCase(type)){
                worldManager.add(object);
                System.out.println(worldManager.getRightBaffle());
                return true;
            }
            if(length >= 5) {
                if(type.endsWith("Pipe")){
                    int angle = Integer.parseInt(messages[4]);
                    for(int i = 0; i < angle; i++) object.rotate();
                } else {
                    boolean isLarge = Boolean.parseBoolean(messages[4]);
                    if(isLarge) object.magnify();
                    if(type.equalsIgnoreCase("Ball")) {
                        worldManager.add(object);
                        return true;
                    }
                }
            }
            if(length == 6) {
                int angle = Integer.parseInt(messages[5]);
                for(int i = 0; i < angle; i++) object.rotate();
            }
            worldManager.add(object);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
