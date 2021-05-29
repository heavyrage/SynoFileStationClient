package com.heavyrage.syno.client.exceptions;

public class CompatibilityException extends Exception {
    public CompatibilityException() {
        super("This call is not compatible with the api version used by the remote DiskStation");
    }

    public  CompatibilityException(String message) {
        super(message);
    }
}
