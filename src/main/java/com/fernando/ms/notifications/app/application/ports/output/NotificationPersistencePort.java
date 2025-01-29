package com.fernando.ms.notifications.app.application.ports.output;

import com.fernando.ms.notifications.app.domain.models.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;

public interface NotificationPersistencePort {
    Flux<Notification> findAllByUser(Long id,Long page,Long size);
    Mono<Notification> save(Notification notification);
    Mono<Notification> findById(String id);
    Mono<Notification> update(Notification notification);
    Flux<Notification> save(Iterable<Notification> notifications);
}
