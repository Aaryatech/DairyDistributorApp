package com.ats.dairydistributorapp.model;

public class ErrorMessage {

    private String message;
    private boolean isError;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", isError=" + isError +
                '}';
    }
}
