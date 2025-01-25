package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.repository;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface NotificationReactiveMongoRepository extends ReactiveMongoRepository<NotificationDocument,String> ,NotificationReactiveMongoRepositoryCustom{

    Flux<NotificationDocument> findAllByUserId(Long userId);

}
