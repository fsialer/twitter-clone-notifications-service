package com.fernando.ms.notifications.app.application.ports.output;

import com.fernando.ms.notifications.app.domain.models.Notification;
import reactor.core.publisher.Flux;

import java.util.List;

public interface NotificationPersistencePort {
    Flux<Notification> findAllByUser(Long id,Long page,Long size);
}
