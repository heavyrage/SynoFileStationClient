package com.heavyrage.syno.client.exceptions;

public class ResponseException extends Exception {

    public ResponseException() {
        super("An error occured while getting data, please check API version compatibility");
    }

    public ResponseException(String message) {
        super(message);
    }
}
