package me.ai.playground.mcp_chat.orders_chat.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chat;

    public ChatController(ChatClient chat) {
        this.chat = chat;
    }

    @PostMapping
    public Map<String, Object> chat(@RequestBody Map<String, String> body) {
        String userMsg = body.getOrDefault("message",
                "List the top 5 orders with id, productName, quantity.");
        var content = chat
                .prompt()
                .user(userMsg)
                .call()
                .content();

        return Map.of("answer", content);
    }
}