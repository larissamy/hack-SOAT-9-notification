package com.hack_SOAT_9.notification_service.domain;

public class ErrorNotificationMessage {

    private String email;
    private String errorMessage;

    public ErrorNotificationMessage() {}

    public ErrorNotificationMessage(String email, String errorMessage) {
        this.email = email;
        this.errorMessage = errorMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}