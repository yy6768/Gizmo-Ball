package com.example.backend.consumer;


import com.example.backend.consumer.utils.Game;
import com.example.backend.physicsInterface.BallWorld;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint("/websocket/")
public class WebSocketServer {

    // 每个链接用session维护
    private Session session = null;

    private final Game game = null;

    private final BallWorld initWorld = null;

    private void startGame(){

    }

    private void endGame() {

    }

    private void initLayout() {
        //包装好的world需要实现toString方法
        if(initWorld != null) {
            sendMessage(initWorld.toString());
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        System.out.println("connected");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("disconnected");
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {

        System.out.println("received  " + message);
        if(message == null || message.length() == 0) throw  new IOException();

        if ("startGame".equals(message)) {
            startGame();
        } else if("layoutMode".equalsIgnoreCase(message)) {
            endGame();
            initLayout();
        } else if(message.startsWith("lay")){
            String[] messages = message.split(" ");
//            initWorld.add();

        }
//        else {
//            throw new IOException();
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
