package com.example.backend.consumer;


import com.example.backend.consumer.utils.Game;
import com.example.backend.exception.MessageException;
import com.example.backend.physics.WorldConstant;
import com.example.backend.physics.WorldManager;
import com.example.backend.physicsInterface.GizmoObject;
import com.example.backend.physicsInterface.GizmoWorld;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Constructor;


/**
 * 前后端建立连接通信
 * 发送消息，后端实时判断信息并完成渲染
 */
@Component
@ServerEndpoint("/websocket/")
public class WebSocketServer {

    // 每个链接用session维护
    private Session session = null;

    private Game game = null;

    private final WorldManager worldManager = new WorldManager();

    //启动游戏
    private void startGame() {
        game = new Game(this, worldManager);
        game.start();
    }
    //结束游戏
    private void endGame() {
        game.setDone(true);
    }

    //载入布局
    private void initLayout() {
        //包装好的world需要实现toString方法
        sendMessage(worldManager.toString());
    }

    //开启连接
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("connected");
    }

    //关闭连接
    @OnClose
    public void onClose(Session session) {
        System.out.println("disconnected");
    }

    /**
     * 接受消息
     * 分为几类消息：
     * 开启游戏 startGame
     * 关闭游戏 endGame
     * 添加组件 add
     * 删除组件 delete
     * 旋转组件 rotate
     * 放大组件 magnify
     * 缩小组件 shrink
     * @param message 接受到的消息
     * @param session websocket的session信息
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("received " + message);
        if (message == null || message.length() == 0) throw new IOException();

        if ("startGame".equals(message)) {
            startGame();
        } else if ("endGame".equalsIgnoreCase(message)) {
            endGame();
            initLayout();
        } else if (message.startsWith("add")) {
            addComponents(message);
        } else if (message.startsWith("delete")) {
            String[] messages = message.split(" ");
            int id = Integer.parseInt(messages[1]);
            worldManager.delete(id);
        } else if (message.startsWith("rotate")) {
            String[] messages = message.split(" ");
            int id = Integer.parseInt(messages[1]);
            GizmoObject object = worldManager.get(id);
            object.rotate();
        } else if (message.startsWith("magnify")) {
            String[] messages = message.split(" ");
            int id = Integer.parseInt(messages[1]);
            GizmoObject object = worldManager.get(id);
            object.magnify();
        } else if (message.startsWith("shrink")) {
            String[] messages = message.split(" ");
            int id = Integer.parseInt(messages[1]);
            GizmoObject object = worldManager.get(id);
            object.shrink();
        } else {
            throw new MessageException("websocket信息处理错误");
        }
    }

    /**
     * 添加组件（因为逻辑复杂单独分离出一个私有函数）
     * @param message
     */
    private void addComponents(String message) {
        String[] messages = message.split(" ");
        //类型
        String type = messages[1];
        //组件的id
        int id = Integer.parseInt(messages[2]);
        //组件的横坐标
        float x = Float.parseFloat(messages[3]) * WorldConstant.LENGTH;
        //组件的纵坐标，因为物理引擎和画布上下颠倒，所以需要使用画布高度减当前的坐标
        float y = WorldConstant.HIGHT - Float.parseFloat(messages[4]) * WorldConstant.LENGTH;
        System.out.println(x + "" + y);
        Class<?> objectType;
        try {
            //反射动态创建组件
            objectType = Class.forName("com.example.backend.physics.objs.Gizmo" + type);
            Constructor<?> constructor = objectType.getConstructor(Integer.class, Float.class, Float.class);
            GizmoObject object = (GizmoObject) constructor.newInstance(id, x, y);
            worldManager.add(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 向前端发送消息
     * @param message 输出的消息
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
