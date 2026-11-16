package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.NewsId;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewsCreatedDomainEvent extends DomainEvent {

    NewsId newsId;
    String categoria;
    String periodista;

    private NewsCreatedDomainEvent(
            final NewsId newsId, final String categoria, final String periodista) {
        super("news.created");
        this.newsId = newsId;
        this.categoria = categoria;
        this.periodista = periodista;
    }

    public static NewsCreatedDomainEvent of(
            final NewsId newsId, final String categoria, final String periodista) {
        return new NewsCreatedDomainEvent(newsId, categoria, periodista);
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "newsId", newsId.value(),
                "categoria", categoria,
                "periodista", periodista);
    }
}
