package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NewsModel;
import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import java.util.Optional;

public interface GetNewsByIdPort {
    Optional<NewsModel> getById(NewsId newsId);
}