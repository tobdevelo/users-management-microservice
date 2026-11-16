package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.NivelPublico;
import com.jcaa.usersmanagement.domain.exception.InvalidNewsDataException;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import java.time.LocalDate;
import lombok.Value;

@Value
public class NewsModel {

    NewsId id;
    String categoria;
    LocalDate fecha;
    String pais;
    String departamento;
    String ciudad;
    String periodista;
    String programaEmite;
    LocalDate fechaEmision;
    String descripcion;
    NivelPublico nivelPublico;

    public static NewsModel create(
            final NewsId id,
            final String categoria,
            final LocalDate fecha,
            final String pais,
            final String departamento,
            final String ciudad,
            final String periodista,
            final String programaEmite,
            final LocalDate fechaEmision,
            final String descripcion,
            final NivelPublico nivelPublico) {
        validateRequiredFields(
                categoria, fecha, pais, departamento, ciudad, periodista, programaEmite, fechaEmision, descripcion, nivelPublico);
        return new NewsModel(
                id,
                categoria.trim(),
                fecha,
                pais.trim(),
                departamento.trim(),
                ciudad.trim(),
                periodista.trim(),
                programaEmite.trim(),
                fechaEmision,
                descripcion.trim(),
                nivelPublico);
    }

    private static void validateRequiredFields(
            final String categoria,
            final LocalDate fecha,
            final String pais,
            final String departamento,
            final String ciudad,
            final String periodista,
            final String programaEmite,
            final LocalDate fechaEmision,
            final String descripcion,
            final NivelPublico nivelPublico) {
        if (categoria == null || categoria.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("categoria");
        }
        if (fecha == null) {
            throw InvalidNewsDataException.becauseFieldIsNull("fecha");
        }
        if (pais == null || pais.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("pais");
        }
        if (departamento == null || departamento.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("departamento");
        }
        if (ciudad == null || ciudad.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("ciudad");
        }
        if (periodista == null || periodista.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("periodista");
        }
        if (programaEmite == null || programaEmite.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("programaEmite");
        }
        if (fechaEmision == null) {
            throw InvalidNewsDataException.becauseFieldIsNull("fechaEmision");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw InvalidNewsDataException.becauseFieldIsBlank("descripcion");
        }
        if (nivelPublico == null) {
            throw InvalidNewsDataException.becauseFieldIsNull("nivelPublico");
        }
    }
}