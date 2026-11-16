package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateNewsCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNewsCommand;
import com.jcaa.usersmanagement.domain.enums.NivelPublico;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;

public final class NewsApplicationMapper {

    private NewsApplicationMapper() {}

    public static NewsModel toModel(final CreateNewsCommand command) {
        return NewsModel.create(
                NewsId.generate(),
                command.categoria(),
                command.fecha(),
                command.pais(),
                command.departamento(),
                command.ciudad(),
                command.periodista(),
                command.programaEmite(),
                command.fechaEmision(),
                command.descripcion(),
                NivelPublico.fromString(command.nivelPublico()));
    }

    public static NewsModel toModel(final UpdateNewsCommand command) {
        return NewsModel.create(
                NewsId.of(command.id()),
                command.categoria(),
                command.fecha(),
                command.pais(),
                command.departamento(),
                command.ciudad(),
                command.periodista(),
                command.programaEmite(),
                command.fechaEmision(),
                command.descripcion(),
                NivelPublico.fromString(command.nivelPublico()));
    }
}