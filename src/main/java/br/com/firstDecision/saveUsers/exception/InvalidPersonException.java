package br.com.firstDecision.saveUsers.exception;

import lombok.Getter;

@Getter
public class InvalidPersonException extends RuntimeException {

    public InvalidPersonException(String message) {
        super(message);
    }
}

