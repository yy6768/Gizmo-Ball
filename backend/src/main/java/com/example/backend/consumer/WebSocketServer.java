package com.example.backend.consumer;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {

    // 每个链接用session维护
    private Session session = null;

    @OnOpen
    public void onOpen(Session session) throws IOException{
        this.session = session;
        System.out.println("connected");
    }

    @OnClose
    public void onClose(Session session) throws IOException{
        System.out.println("disconnected");
    }

    @OnMessage
    public void onMessage(Session session) throws IOException{
        System.out.println("received");
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
