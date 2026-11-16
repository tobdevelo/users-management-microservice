package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.time.LocalDate;

public record NewsPersistenceDto(
        String id,
        String categoria,
        LocalDate fecha,
        String pais,
        String departamento,
        String ciudad,
        String periodista,
        String programaEmite,
        LocalDate fechaEmision,
        String descripcion,
        String nivelPublico) {}