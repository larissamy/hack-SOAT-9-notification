package com.hack_SOAT_9.notification_service.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ErrorNotificationListenerTest {

    private JavaMailSender mailSender;
    private ErrorNotificationListener listener;

    @BeforeEach
    void setUp() {
        mailSender = mock(JavaMailSender.class);
        listener = new ErrorNotificationListener(mailSender, "fiap@fiapx.com", "Erro");
    }

    @Test
    @DisplayName("Deve enviar e-mail com informações corretas ao receber mensagem de erro")
    void shouldSendEmailWhenErrorMessageReceived() {
        // Arrange
        Map<String, String> message = new HashMap<>();
        message.put("email", "jon@email.com");
        message.put("errorMessage", "Erro no processamento");

        // Act
        listener.handleError(message);

        // Assert
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage email = captor.getValue();
        assertEquals("jon@email.com", email.getTo()[0]);
        assertEquals("fiap@fiapx.com", email.getFrom());
        assertEquals("Erro", email.getSubject());
        assertTrue(email.getText().contains("Erro no processamento"));
    }
}
