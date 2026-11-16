package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateNewsUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveNewsPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateNewsCommand;
import com.jcaa.usersmanagement.application.service.mapper.NewsApplicationMapper;
import com.jcaa.usersmanagement.domain.event.NewsCreatedDomainEvent;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateNewsService implements CreateNewsUseCase {

    private final SaveNewsPort saveNewsPort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public NewsModel execute(final CreateNewsCommand command) {
        final NewsModel newsToCreate = NewsApplicationMapper.toModel(command);
        final NewsModel savedNews = saveNewsPort.save(newsToCreate);
        eventPublisher.publishEvent(
                NewsCreatedDomainEvent.of(
                        savedNews.getId(), savedNews.getCategoria(), savedNews.getPeriodista()));
        log.info("Noticia creada con ID: {}", savedNews.getId().value());
        return savedNews;
    }
}