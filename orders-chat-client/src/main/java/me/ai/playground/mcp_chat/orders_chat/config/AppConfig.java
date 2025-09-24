package me.ai.playground.mcp_chat.orders_chat.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder baseBuilder, ToolCallbackProvider tools) {
        return baseBuilder
                .defaultToolCallbacks(tools)
                .defaultSystem("""
                  You are a helpful assistant.
                  You can call MCP tools from the 'orders' connection when helpful.
                  Prefer tools over guessing.
                  """)
                .build();
    }
}
