package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.NivelPublico;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.NewsPersistenceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class NewsPersistenceMapper {

    private NewsPersistenceMapper() {}

    public static NewsPersistenceDto fromModelToDto(final NewsModel model) {
        return new NewsPersistenceDto(
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

    public static NewsModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return NewsModel.create(
                NewsId.of(rs.getString("id")),
                rs.getString("categoria"),
                rs.getDate("fecha").toLocalDate(),
                rs.getString("pais"),
                rs.getString("departamento"),
                rs.getString("ciudad"),
                rs.getString("periodista"),
                rs.getString("programa_emite"),
                rs.getDate("fecha_emision").toLocalDate(),
                rs.getString("descripcion"),
                NivelPublico.fromString(rs.getString("nivel_publico")));
    }

    public static List<NewsModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<NewsModel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fromResultSetToModel(rs));
        }
        return list;
    }
}