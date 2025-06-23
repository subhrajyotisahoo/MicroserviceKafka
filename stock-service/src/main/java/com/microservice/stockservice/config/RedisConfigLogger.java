package com.microservice.stockservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConfigLogger {

    @Value("${spring.redis.host}")
    private String redisHost;

    @PostConstruct
    public void logRedisHost() {
        System.out.println("🔥 Resolved Redis Host: " + redisHost);
    }
}
