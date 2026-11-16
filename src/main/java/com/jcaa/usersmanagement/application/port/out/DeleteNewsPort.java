package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.NewsId;

public interface DeleteNewsPort {
    void delete(NewsId newsId);
}