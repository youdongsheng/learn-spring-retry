package com.bc.consumer.exception;

public class ConsumerException extends Exception {
    private String errorCode;

    public ConsumerException() {
        super();
    }

    public ConsumerException(Throwable e) {
        super(e);
    }

    public ConsumerException(String message) {
        super(message);
    }

    public ConsumerException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ConsumerException(String message, Throwable e) {
        super(message, e);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

}
