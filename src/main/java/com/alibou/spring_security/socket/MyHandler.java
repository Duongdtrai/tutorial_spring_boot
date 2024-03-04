package com.alibou.spring_security.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Xử lý tin nhắn từ client
        String clientMessage = message.getPayload();
        System.out.println("Received message from client: " + clientMessage);
        session.sendMessage(new TextMessage("Server received you messsage: "+clientMessage ));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Khi kết nối WebSocket được thiết lập
        System.out.println("WebSocket connection established.");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Khi kết nối WebSocket bị đóng
        System.out.println("WebSocket connection closed. Status: " + status);
    }
}
