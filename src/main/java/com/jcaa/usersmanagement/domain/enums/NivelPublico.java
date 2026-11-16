package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidNewsDataException;

public enum NivelPublico {
    GENERAL,
    INFANTIL,
    JUVENIL,
    ADULTO,
    ESPECIALIZADO;

    public static NivelPublico fromString(final String value) {
        if (value == null || value.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("nivelPublico");
        }
        try {
            return NivelPublico.valueOf(value.trim().toUpperCase());
        } catch (final IllegalArgumentException ex) {
            throw InvalidNewsDataException.becauseNivelPublicoIsInvalid(value);
        }
    }
}