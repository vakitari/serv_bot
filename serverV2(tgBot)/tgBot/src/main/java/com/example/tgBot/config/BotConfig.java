package com.example.tgBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

    @Configuration
    @Data
    @PropertySource("config.properties")
    public class BotConfig{
        @Value("${bot.name}") private String botName;
        @Value("${bot.token}") private String token;
        @Value("${bot.chatId}") private String chatId;
    }
