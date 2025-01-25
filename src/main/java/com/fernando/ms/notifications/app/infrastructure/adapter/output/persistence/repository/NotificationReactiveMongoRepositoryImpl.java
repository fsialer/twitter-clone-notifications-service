package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.repository;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class NotificationReactiveMongoRepositoryImpl implements NotificationReactiveMongoRepositoryCustom{
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<NotificationDocument> findAllByUserIdPaginated(Long userId, Long page, Long size) {
        Query query = new Query()
                .addCriteria(Criteria.where("userId").is(userId)) // Filtrar por autor
                .skip((long) page * size)                            // Paginación: saltar los resultados
                .limit(Math.toIntExact(size));                                        // Limitar los resultados
        return mongoTemplate.find(query, NotificationDocument.class);
    }
}
