package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateNewsUseCase;
import com.jcaa.usersmanagement.application.port.out.GetNewsByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateNewsPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateNewsCommand;
import com.jcaa.usersmanagement.application.service.mapper.NewsApplicationMapper;
import com.jcaa.usersmanagement.domain.event.NewsUpdatedDomainEvent;
import com.jcaa.usersmanagement.domain.exception.NewsNotFoundException;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateNewsService implements UpdateNewsUseCase {

    private final GetNewsByIdPort getNewsByIdPort;
    private final UpdateNewsPort updateNewsPort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public NewsModel execute(final UpdateNewsCommand command) {
        final NewsId newsId = NewsId.of(command.id());
        getNewsByIdPort
                .getById(newsId)
                .orElseThrow(() -> NewsNotFoundException.becauseIdWasNotFound(newsId.value()));

        final NewsModel newsToUpdate = NewsApplicationMapper.toModel(command);
        final NewsModel updatedNews = updateNewsPort.update(newsToUpdate);
        eventPublisher.publishEvent(
                NewsUpdatedDomainEvent.of(updatedNews.getId(), updatedNews.getCategoria()));
        log.info("Noticia actualizada con ID: {}", updatedNews.getId().value());
        return updatedNews;
    }
}