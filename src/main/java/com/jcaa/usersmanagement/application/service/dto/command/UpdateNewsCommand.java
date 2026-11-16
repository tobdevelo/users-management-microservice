package com.jcaa.usersmanagement.application.service.dto.command;

import java.time.LocalDate;

public record UpdateNewsCommand(
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