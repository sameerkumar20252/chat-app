package com.chatApp.app.controller;

import com.chatApp.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        if (message.getContent() != null && message.getContent().length() > 500) {
            message.setContent(message.getContent().substring(0, 500));
        }
        if (message.getSender() != null && message.getSender().length() > 30) {
            message.setSender(message.getSender().substring(0, 30));
        }
        return message;
    }
    @GetMapping("/chat")
    public String index() {
        return "chat";   // templates/index.html
    }
    // /app/clearChat
    @MessageMapping("/clearChat")
    @SendTo("/topic/clear")
    public String clearChat() {
        return "cleared";
    }
}
