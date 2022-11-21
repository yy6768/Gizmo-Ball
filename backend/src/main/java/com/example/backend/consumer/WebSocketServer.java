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

    private void startGame() {
        game = new Game(worldManager);
        game.start();
    }

    private void endGame() {

    }

    private void initLayout() {
        //包装好的world需要实现toString方法
        sendMessage(worldManager.toString());
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("connected");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("disconnected");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("received " + message);
        if (message == null || message.length() == 0) throw new IOException();

        if ("startGame".equals(message)) {
            startGame();
        } else if ("layoutMode".equalsIgnoreCase(message)) {
            endGame();
            initLayout();
        } else if (message.startsWith("add")) {
            String[] messages = message.split(" ");
            String type = messages[1];
            int id = Integer.parseInt(messages[2]);
            float x = Float.parseFloat(messages[3]) * WorldConstant.LENGTH;
            float y = Float.parseFloat(messages[4]) * WorldConstant.LENGTH;
            Class<?> objectType;
            try {
                objectType = Class.forName("com.example.backend.physics.objs.Gizmo" + type);
                Constructor<?> constructor = objectType.getConstructor(Integer.class, Float.class, Float.class);
                GizmoObject object = (GizmoObject) constructor.newInstance(id, x, y);
                worldManager.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
//        for (GizmoObject object : worldManager.getAll()) {
//            System.out.println(object.toString());
//        }

    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
