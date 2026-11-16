package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateNewsRestRequest(
        @NotBlank(message = "La categoría es obligatoria") String categoria,
        @NotNull(message = "La fecha es obligatoria") LocalDate fecha,
        @NotBlank(message = "El país es obligatorio") String pais,
        @NotBlank(message = "El departamento es obligatorio") String departamento,
        @NotBlank(message = "La ciudad es obligatoria") String ciudad,
        @NotBlank(message = "El periodista es obligatorio") String periodista,
        @NotBlank(message = "El programa que emite es obligatorio") String programaEmite,
        @NotNull(message = "La fecha de emisión es obligatoria") LocalDate fechaEmision,
        @NotBlank(message = "La descripción es obligatoria") String descripcion,
        @NotBlank(message = "El nivel de público es obligatorio") String nivelPublico) {}