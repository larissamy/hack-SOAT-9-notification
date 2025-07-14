package com.hack_SOAT_9.notification_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue errorQueue() {
        return new Queue("notification.errors", true);
    }

    @Bean
    public TopicExchange errorExchange() {
        return new TopicExchange("video.errors");
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, TopicExchange errorExchange) {
        return BindingBuilder.bind(errorQueue).to(errorExchange).with("");
    }
}