package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateNewsCommand;
import com.jcaa.usersmanagement.domain.model.NewsModel;

public interface CreateNewsUseCase {
    NewsModel execute(CreateNewsCommand command);
}