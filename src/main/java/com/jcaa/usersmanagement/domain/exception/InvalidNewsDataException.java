package com.jcaa.usersmanagement.domain.exception;

public class InvalidNewsDataException extends DomainException {

    private InvalidNewsDataException(final String message) {
        super(message);
    }

    public static InvalidNewsDataException becauseFieldIsBlank(final String fieldName) {
        return new InvalidNewsDataException("El campo '" + fieldName + "' de la noticia es obligatorio.");
    }

    public static InvalidNewsDataException becauseFieldIsNull(final String fieldName) {
        return new InvalidNewsDataException("El campo '" + fieldName + "' de la noticia no puede ser nulo.");
    }

    public static InvalidNewsDataException becauseIdFormatIsInvalid(final String id) {
        return new InvalidNewsDataException("El identificador de noticia no es un UUID válido: " + id);
    }

    public static InvalidNewsDataException becauseNivelPublicoIsInvalid(final String value) {
        return new InvalidNewsDataException(
                "Nivel de público inválido: '" + value + "'. Valores permitidos: GENERAL, INFANTIL, JUVENIL, ADULTO, ESPECIALIZADO.");
    }
}