package com.example.backend.service.impl;

import com.example.backend.consumer.WebSocketServer;
import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;
import com.example.backend.service.UploadGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

@Service
public class UploadGameServiceImpl implements UploadGameService {
    @Autowired
    private WebSocketServer webSocketServer;
    @Override
    public JSONObject uploadGame(MultipartFile file) throws Exception{
        JSONObject result = new JSONObject();
        InputStream is = file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        int id = 0;
        WorldManager worldManager = new WorldManager();
        while((line = reader.readLine()) != null){
            boolean res = addComponents(line, id, worldManager);
            if(!res){
                result.put("result","fail");
            }
            id ++;
        }
        webSocketServer.setWorldManager(worldManager);
        result.put("result","success");
        return result;
    }

    private boolean addComponents(String message, Integer id, WorldManager manager) {
        String[] messages = message.split(" ");
        int length = messages.length;
        //类型
        String type = messages[0];
        //组件的横坐标
        float x = Float.parseFloat(messages[1]) * WorldConstant.LENGTH;
        //组件的纵坐标，因为物理引擎和画布上下颠倒，所以需要使用画布高度减当前的坐标
        float y = WorldConstant.HIGHT - Float.parseFloat(messages[2]) * WorldConstant.LENGTH;
        System.out.println(x + "" + y);
        Class<?> objectType;
        try {
            //反射动态创建组件
            objectType = Class.forName("com.example.backend.physics.objs.Gizmo" + type);
            Constructor<?> constructor = objectType.getConstructor(Integer.class, Float.class, Float.class);
            GizmoObject object = (GizmoObject) constructor.newInstance(id, x, y);
            if(length == 4) {
                boolean isLarge = Boolean.parseBoolean(messages[3]);
                if(isLarge) object.magnify();
            } else if(length == 5) {
                int angle = Integer.parseInt(messages[4]);
                for(int i = 0; i < angle; i++) object.rotate();
            } else {
                return false;
            }
            manager.add(object);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
