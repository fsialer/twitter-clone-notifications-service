package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.repository;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import reactor.core.publisher.Flux;

public interface NotificationReactiveMongoRepositoryCustom {
    Flux<NotificationDocument> findAllByUserIdPaginated(Long userId,Long page,Long size);
}
