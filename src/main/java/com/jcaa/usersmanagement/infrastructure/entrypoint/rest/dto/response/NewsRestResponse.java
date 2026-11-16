package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

import java.time.LocalDate;

public record NewsRestResponse(
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