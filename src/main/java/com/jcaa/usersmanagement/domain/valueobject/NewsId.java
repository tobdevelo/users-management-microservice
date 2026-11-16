package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNewsDataException;
import java.util.UUID;

public record NewsId(String value) {

    public NewsId {
        if (value == null || value.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("id");
        }
        try {
            UUID.fromString(value.trim());
        } catch (final IllegalArgumentException ex) {
            throw InvalidNewsDataException.becauseIdFormatIsInvalid(value);
        }
        value = value.trim();
    }

    public static NewsId generate() {
        return new NewsId(UUID.randomUUID().toString());
    }

    public static NewsId of(final String value) {
        return new NewsId(value);
    }
}