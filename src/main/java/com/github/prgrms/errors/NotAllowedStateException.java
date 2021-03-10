package com.github.prgrms.errors;

public class NotAllowedStateException extends RuntimeException{

    public NotAllowedStateException(String message) {
        super(message);
    }

    public NotAllowedStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
