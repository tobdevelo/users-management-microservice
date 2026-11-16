package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteNewsPort;
import com.jcaa.usersmanagement.application.port.out.GetAllNewsPort;
import com.jcaa.usersmanagement.application.port.out.GetNewsByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveNewsPort;
import com.jcaa.usersmanagement.application.port.out.UpdateNewsPort;
import com.jcaa.usersmanagement.domain.exception.NewsNotFoundException;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.NewsPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.NewsPersistenceMapper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NewsRepositoryMySQL
        implements SaveNewsPort, UpdateNewsPort, GetNewsByIdPort, GetAllNewsPort, DeleteNewsPort {

    private static final String SQL_INSERT =
            "INSERT INTO noticias "
                    + "(id, categoria, fecha, pais, departamento, ciudad, periodista, programa_emite, fecha_emision, descripcion, nivel_publico, created_at, updated_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE noticias SET categoria = ?, fecha = ?, pais = ?, departamento = ?, ciudad = ?, "
                    + "periodista = ?, programa_emite = ?, fecha_emision = ?, descripcion = ?, nivel_publico = ?, updated_at = NOW() "
                    + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, categoria, fecha, pais, departamento, ciudad, periodista, programa_emite, fecha_emision, descripcion, nivel_publico "
                    + "FROM noticias WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, categoria, fecha, pais, departamento, ciudad, periodista, programa_emite, fecha_emision, descripcion, nivel_publico "
                    + "FROM noticias ORDER BY fecha DESC";

    private static final String SQL_DELETE = "DELETE FROM noticias WHERE id = ?";

    private final DataSource dataSource;

    @Override
    public NewsModel save(final NewsModel news) {
        final NewsPersistenceDto dto = NewsPersistenceMapper.fromModelToDto(news);
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.categoria());
            statement.setDate(3, Date.valueOf(dto.fecha()));
            statement.setString(4, dto.pais());
            statement.setString(5, dto.departamento());
            statement.setString(6, dto.ciudad());
            statement.setString(7, dto.periodista());
            statement.setString(8, dto.programaEmite());
            statement.setDate(9, Date.valueOf(dto.fechaEmision()));
            statement.setString(10, dto.descripcion());
            statement.setString(11, dto.nivelPublico());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed(dto.id(), exception);
        }
        return findByIdOrFail(news.getId());
    }

    @Override
    public NewsModel update(final NewsModel news) {
        final NewsPersistenceDto dto = NewsPersistenceMapper.fromModelToDto(news);
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.categoria());
            statement.setDate(2, Date.valueOf(dto.fecha()));
            statement.setString(3, dto.pais());
            statement.setString(4, dto.departamento());
            statement.setString(5, dto.ciudad());
            statement.setString(6, dto.periodista());
            statement.setString(7, dto.programaEmite());
            statement.setDate(8, Date.valueOf(dto.fechaEmision()));
            statement.setString(9, dto.descripcion());
            statement.setString(10, dto.nivelPublico());
            statement.setString(11, dto.id());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
        }
        return findByIdOrFail(news.getId());
    }

    @Override
    public Optional<NewsModel> getById(final NewsId newsId) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, newsId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(NewsPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(newsId.value(), exception);
        }
    }

    @Override
    public List<NewsModel> getAll() {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return NewsPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public void delete(final NewsId newsId) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, newsId.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(newsId.value(), exception);
        }
    }

    private NewsModel findByIdOrFail(final NewsId newsId) {
        return getById(newsId)
                .orElseThrow(() -> NewsNotFoundException.becauseIdWasNotFound(newsId.value()));
    }
}