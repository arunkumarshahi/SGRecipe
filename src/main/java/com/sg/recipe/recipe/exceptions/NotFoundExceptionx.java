package com.sg.recipe.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionx extends RuntimeException {
    public NotFoundExceptionx() {
        super();
    }

    public NotFoundExceptionx(String message) {
        super(message);
    }

    public NotFoundExceptionx(String message, Throwable throwable) {
        super(message, throwable);
    }

}
