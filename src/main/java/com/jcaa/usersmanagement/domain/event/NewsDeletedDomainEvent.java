package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewsDeletedDomainEvent extends DomainEvent {

    NewsId newsId;

    private NewsDeletedDomainEvent(final NewsId newsId) {
        super("news.deleted");
        this.newsId = newsId;
    }

    public static NewsDeletedDomainEvent of(final NewsId newsId) {
        return new NewsDeletedDomainEvent(newsId);
    }

    @Override
    public Map<String, String> payload() {
        return Map.of("newsId", newsId.value());
    }
}