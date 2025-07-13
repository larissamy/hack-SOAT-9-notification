package com.hack_SOAT_9.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NotificationServiceApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplicationTest.class, args);
    }
}
