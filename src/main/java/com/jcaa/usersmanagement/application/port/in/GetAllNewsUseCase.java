package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.NewsModel;
import java.util.List;

public interface GetAllNewsUseCase {
    List<NewsModel> execute();
}