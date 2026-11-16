package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllNewsUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllNewsPort;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllNewsService implements GetAllNewsUseCase {

    private final GetAllNewsPort getAllNewsPort;

    @Override
    public List<NewsModel> execute() {
        return getAllNewsPort.getAll();
    }
}