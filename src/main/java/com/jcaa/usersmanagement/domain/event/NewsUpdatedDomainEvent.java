package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewsUpdatedDomainEvent extends DomainEvent {

    NewsId newsId;
    String categoria;

    private NewsUpdatedDomainEvent(final NewsId newsId, final String categoria) {
        super("news.updated");
        this.newsId = newsId;
        this.categoria = categoria;
    }

    public static NewsUpdatedDomainEvent of(final NewsId newsId, final String categoria) {
        return new NewsUpdatedDomainEvent(newsId, categoria);
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "newsId", newsId.value(),
                "categoria", categoria);
    }
}