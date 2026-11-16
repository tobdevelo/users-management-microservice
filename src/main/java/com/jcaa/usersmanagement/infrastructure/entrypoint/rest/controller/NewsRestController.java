package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateNewsUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteNewsUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllNewsUseCase;
import com.jcaa.usersmanagement.application.port.in.GetNewsByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateNewsUseCase;
import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateNewsRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.NewsRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.NewsRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/noticias")
@RequiredArgsConstructor
public class NewsRestController implements NewsRestControllerDocs {

    private final CreateNewsUseCase createNewsUseCase;
    private final UpdateNewsUseCase updateNewsUseCase;
    private final DeleteNewsUseCase deleteNewsUseCase;
    private final GetNewsByIdUseCase getNewsByIdUseCase;
    private final GetAllNewsUseCase getAllNewsUseCase;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsRestResponse create(@Valid @RequestBody final CreateNewsRestRequest request) {
        final NewsModel news = createNewsUseCase.execute(NewsRestMapper.toCreateCommand(request));
        return NewsRestMapper.toResponse(news);
    }

    @Override
    @GetMapping
    public List<NewsRestResponse> getAll() {
        return NewsRestMapper.toResponseList(getAllNewsUseCase.execute());
    }

    @Override
    @GetMapping("/{id}")
    public NewsRestResponse getById(@PathVariable final String id) {
        final NewsModel news = getNewsByIdUseCase.execute(NewsRestMapper.toGetByIdQuery(id));
        return NewsRestMapper.toResponse(news);
    }

    @Override
    @PutMapping("/{id}")
    public NewsRestResponse update(
            @PathVariable final String id, @Valid @RequestBody final UpdateNewsRestRequest request) {
        final NewsModel news = updateNewsUseCase.execute(NewsRestMapper.toUpdateCommand(id, request));
        return NewsRestMapper.toResponse(news);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String id) {
        deleteNewsUseCase.execute(NewsRestMapper.toDeleteCommand(id));
    }
}