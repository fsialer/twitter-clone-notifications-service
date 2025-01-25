package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence;

import com.fernando.ms.notifications.app.application.ports.output.NotificationPersistencePort;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.mapper.NotificationPersistenceMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.repository.NotificationReactiveMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPersistencePort {
    private final NotificationReactiveMongoRepository notificationReactiveMongoRepository;

    private final NotificationPersistenceMapper notificationPersistenceMapper;

    @Override
    public Flux<Notification> findAllByUser(Long id,Long page,Long size) {
        return notificationPersistenceMapper.toNotifications(notificationReactiveMongoRepository.findAllByUserIdPaginated(id,page,size));
    }

    @Override
    public Mono<Notification> save(Notification notification) {
        NotificationDocument notificationDocument=notificationPersistenceMapper.toNotificationDocument(notification);
        return notificationPersistenceMapper.toNotification(notificationReactiveMongoRepository.save(notificationDocument));
    }
}
