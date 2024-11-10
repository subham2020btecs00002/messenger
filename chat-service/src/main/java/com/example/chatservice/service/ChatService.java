package com.example.chatservice.service;

import com.example.chatservice.model.ChatMessage;
import com.example.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public ChatMessage saveMessage(ChatMessage message) {
        return chatRepository.save(message);
    }

    public List<ChatMessage> getMessagesBySender(String sender) {
        return chatRepository.findBySender(sender);
    }
}
