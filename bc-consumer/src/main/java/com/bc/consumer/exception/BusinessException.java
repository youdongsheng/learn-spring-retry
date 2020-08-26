package com.bc.consumer.exception;

public class BusinessException extends ConsumerException {
    public BusinessException() {
        super();
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
