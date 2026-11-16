package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.NewsModel;
import java.util.List;

public interface GetAllNewsPort {
    List<NewsModel> getAll();
}