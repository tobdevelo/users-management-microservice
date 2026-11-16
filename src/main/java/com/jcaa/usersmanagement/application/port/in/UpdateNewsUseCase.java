package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateNewsCommand;
import com.jcaa.usersmanagement.domain.model.NewsModel;

public interface UpdateNewsUseCase {
    NewsModel execute(UpdateNewsCommand command);
}
