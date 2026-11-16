package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteNewsCommand;

public interface DeleteNewsUseCase {
    void execute(DeleteNewsCommand command);
}