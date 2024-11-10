package com.example.chatservice.controller;

import com.example.chatservice.model.ChatMessage;
import com.example.chatservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // REST API Endpoint to send a message (for HTTP request)
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage message) {
        // Save the message (in your MongoDB or whatever storage you're using)
        chatService.saveMessage(message);

        // Return response (could be the saved message or a confirmation)
        return ResponseEntity.ok(message);
    }

    // WebSocket Endpoint to handle real-time messaging
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessageViaWebSocket(ChatMessage message) {
        // Save the message
        chatService.saveMessage(message);

        // Return message to be broadcasted to all connected WebSocket clients
        return message;
    }
}
