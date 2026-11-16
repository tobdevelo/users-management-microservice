package com.jcaa.usersmanagement.domain.exception;

public class NewsNotFoundException extends DomainException {

    private NewsNotFoundException(final String message) {
        super(message);
    }

    public static NewsNotFoundException becauseIdWasNotFound(final String id) {
        return new NewsNotFoundException("No se encontró ninguna noticia con el ID: " + id);
    }
}