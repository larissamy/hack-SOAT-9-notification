package com.hack_SOAT_9.notification_service.consumer;

import com.hack_SOAT_9.notification_service.domain.ErrorNotificationMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ErrorNotificationListener {

    private final JavaMailSender mailSender;
    private final String from;
    private final String subject;

    public ErrorNotificationListener(JavaMailSender mailSender,
                                     @org.springframework.beans.factory.annotation.Value("${notification.email.from}") String from,
                                     @org.springframework.beans.factory.annotation.Value("${notification.email.subject}") String subject) {
        this.mailSender = mailSender;
        this.from = from;
        this.subject = subject;
    }

    @RabbitListener(queues = "${notification.error.queue:video.error.queue}")
    public void handleError(ErrorNotificationMessage message) {
        sendEmail(message);
    }

    private void sendEmail(ErrorNotificationMessage message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(message.getEmail());
        email.setFrom(from);
        email.setSubject(subject);
        email.setText("Olá!\n\nHouve um erro no processamento do seu vídeo:\n\n" +
                      message.getErrorMessage() + "\n\nTente novamente..");
        mailSender.send(email);
    }
}