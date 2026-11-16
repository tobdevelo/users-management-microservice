package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteNewsUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteNewsPort;
import com.jcaa.usersmanagement.application.port.out.GetNewsByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteNewsCommand;
import com.jcaa.usersmanagement.domain.event.NewsDeletedDomainEvent;
import com.jcaa.usersmanagement.domain.exception.NewsNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteNewsService implements DeleteNewsUseCase {

    private final GetNewsByIdPort getNewsByIdPort;
    private final DeleteNewsPort deleteNewsPort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void execute(final DeleteNewsCommand command) {
        final NewsId newsId = NewsId.of(command.id());
        getNewsByIdPort
                .getById(newsId)
                .orElseThrow(() -> NewsNotFoundException.becauseIdWasNotFound(newsId.value()));

        deleteNewsPort.delete(newsId);
        eventPublisher.publishEvent(NewsDeletedDomainEvent.of(newsId));
        log.info("Noticia eliminada con ID: {}", newsId.value());
    }
}