package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetNewsByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetNewsByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetNewsByIdQuery;
import com.jcaa.usersmanagement.domain.exception.NewsNotFoundException;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNewsByIdService implements GetNewsByIdUseCase {

    private final GetNewsByIdPort getNewsByIdPort;

    @Override
    public NewsModel execute(final GetNewsByIdQuery query) {
        final NewsId newsId = NewsId.of(query.id());
        return getNewsByIdPort
                .getById(newsId)
                .orElseThrow(() -> NewsNotFoundException.becauseIdWasNotFound(newsId.value()));
    }
}