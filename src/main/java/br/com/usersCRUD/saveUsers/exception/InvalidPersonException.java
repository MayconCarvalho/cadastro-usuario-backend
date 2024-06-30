package br.com.usersCRUD.saveUsers.exception;

import lombok.Getter;

@Getter
public class InvalidPersonException extends RuntimeException {

    public InvalidPersonException(String message) {
        super(message);
    }
}

