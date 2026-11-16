package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NewsModel;

public interface UpdateNewsPort {
    NewsModel update(NewsModel news);
}