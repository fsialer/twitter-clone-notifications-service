package com.fernando.ms.notifications.app.application.ports.input;

import com.fernando.ms.notifications.app.domain.models.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotificationInputPort {
    Flux<Notification> findAllByUser(Long id,Long page,Long size);
    Mono<Notification> save(Notification notification);
}
