package com.hack_SOAT_9.notification_service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ErrorNotificationListener {

    private final JavaMailSender mailSender;
    private final String from;
    private final String subject;

    public ErrorNotificationListener(JavaMailSender mailSender, String from, String subject) {
        this.mailSender = mailSender;
        this.from = from;
        this.subject = subject;
    }

    @RabbitListener(queues = "notification.errors")
    public void handleError(Map<String, String> message) {
        String email = message.get("email");
        String errorMessage = message.get("errorMessage");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.setText("Erro no processamento do vídeo:\n\n" + errorMessage);

        mailSender.send(mail);
    }
}