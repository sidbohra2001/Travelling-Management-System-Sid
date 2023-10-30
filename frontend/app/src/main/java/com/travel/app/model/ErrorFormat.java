package com.travel.app.model;

public class ErrorFormat {
    private String status;
    private String message;

    public ErrorFormat(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorFormat{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
