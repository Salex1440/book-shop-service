package com.example.bookshopservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectExistsException extends RuntimeException {
    public ObjectExistsException() {
        super();
    }
    public ObjectExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ObjectExistsException(String message) {
        super(message);
    }
    public ObjectExistsException(Throwable cause) {
        super(cause);
    }
}
