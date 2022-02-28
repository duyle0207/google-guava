package com.example.websocket.controller;

import com.example.websocket.dto.Greeting;
import com.example.websocket.dto.HelloMessage;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class GreetingController {

    private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private List<String> lstMessage = new ArrayList<>();

    @PutMapping("/put")
    public List<String> putGreet(HelloMessage message) throws InterruptedException {
        lstMessage.add(message.getName());
        return lstMessage;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public List<String> greet(HelloMessage message) throws InterruptedException {
        lstMessage.add(message.getName());
        return lstMessage;
    }

    Cache<HelloMessage, String> cache;

    @PostConstruct
    public void config() {
        CacheLoader<HelloMessage, String> loader;
        loader = new CacheLoader<HelloMessage, String>() {
            @Override
            public String load(HelloMessage key) {
                return String.format("%s %s", key.getName(), LocalDateTime.now());
            }
        };

        cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(60 * 100, TimeUnit.MILLISECONDS)
                .build(loader);
    }

    @PostMapping("/get-msg")
    public String getMsg(@RequestBody HelloMessage msg) throws ExecutionException {
        return cache.get(msg);
    }
}