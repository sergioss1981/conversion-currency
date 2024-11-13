package br.com.challenge.exception;

import lombok.Getter;

@Getter
public class ExternalApiException extends RuntimeException {

    private int statusCode;
    private String message;

    public ExternalApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
