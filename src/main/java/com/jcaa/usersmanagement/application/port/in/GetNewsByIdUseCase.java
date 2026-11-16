package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetNewsByIdQuery;
import com.jcaa.usersmanagement.domain.model.NewsModel;

public interface GetNewsByIdUseCase {
    NewsModel execute(GetNewsByIdQuery query);
}