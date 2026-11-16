package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateNewsCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteNewsCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNewsCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetNewsByIdQuery;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.NewsRestResponse;
import java.util.List;

public final class NewsRestMapper {

    private NewsRestMapper() {}

    public static CreateNewsCommand toCreateCommand(final CreateNewsRestRequest request) {
        return new CreateNewsCommand(
                request.categoria(),
                request.fecha(),
                request.pais(),
                request.departamento(),
                request.ciudad(),
                request.periodista(),
                request.programaEmite(),
                request.fechaEmision(),
                request.descripcion(),
                request.nivelPublico());
    }

    public static UpdateNewsCommand toUpdateCommand(
            final String id, final UpdateNewsRestRequest request) {
        return new UpdateNewsCommand(
                id,
                request.categoria(),
                request.fecha(),
                request.pais(),
                request.departamento(),
                request.ciudad(),
                request.periodista(),
                request.programaEmite(),
                request.fechaEmision(),
                request.descripcion(),
                request.nivelPublico());
    }

    public static DeleteNewsCommand toDeleteCommand(final String id) {
        return new DeleteNewsCommand(id);
    }

    public static GetNewsByIdQuery toGetByIdQuery(final String id) {
        return new GetNewsByIdQuery(id);
    }

    public static NewsRestResponse toResponse(final NewsModel model) {
        return new NewsRestResponse(
                model.getId().value(),
                model.getCategoria(),
                model.getFecha(),
                model.getPais(),
                model.getDepartamento(),
                model.getCiudad(),
                model.getPeriodista(),
                model.getProgramaEmite(),
                model.getFechaEmision(),
                model.getDescripcion(),
                model.getNivelPublico().name());
    }

    public static List<NewsRestResponse> toResponseList(final List<NewsModel> models) {
        return models.stream().map(NewsRestMapper::toResponse).toList();
    }
}