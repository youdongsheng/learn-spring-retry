package com.bc.consumer.exception;

public class SystemException extends ConsumerException{

    public SystemException() {
        super();
    }

    public SystemException(Throwable e) {
        super(e);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String errorCode, String message) {
        super(errorCode, message);
    }

    public SystemException(String message, Throwable e) {
        super(message, e);
    }

}
