package com.heavyrage.syno.client.exceptions;

public class AuthenticationFailureException extends Exception {
    public AuthenticationFailureException() {
        super();
    }
    public AuthenticationFailureException(String message) {
        super(message);
    }
}
