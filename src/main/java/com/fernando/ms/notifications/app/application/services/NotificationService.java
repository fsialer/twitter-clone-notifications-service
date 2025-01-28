package com.fernando.ms.notifications.app.application.services;

import com.fernando.ms.notifications.app.application.ports.input.NotificationInputPort;
import com.fernando.ms.notifications.app.application.ports.output.ExternalCommentOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.ExternalPostOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.ExternalUserOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.NotificationPersistencePort;
import com.fernando.ms.notifications.app.application.services.strategy.notification.ITargetStrategy;
import com.fernando.ms.notifications.app.domain.exception.NotificationNotFoundException;
import com.fernando.ms.notifications.app.domain.exception.TargetTypeNotFoundException;
import com.fernando.ms.notifications.app.domain.models.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationInputPort {
    private final NotificationPersistencePort notificationPersistencePort;
    private final ExternalUserOutputPort externalUserOutputPort;
    private final List<ITargetStrategy> ITargetStrategyList;



    @Override
    public Flux<Notification> findAllByUser(Long id,Long page,Long size) {
        return notificationPersistencePort.findAllByUser(id, page, size)
                .flatMap(notification -> {
                    ITargetStrategy targetStrategy = ITargetStrategyList.stream()
                            .filter(strategy -> strategy.isApplicable(notification.getTarget().getType()))
                            .findFirst()
                            .orElseThrow(() -> new TargetTypeNotFoundException("Target type ".concat(notification.getTarget().getType()).concat(" no exists.")));
                    return externalUserOutputPort.findById(notification.getUser().getId())
                            .flatMap(user -> targetStrategy.doOperation(notification.getTarget())
                                    .map(target -> {
                                        notification.setTarget(target);
                                        notification.setUser(user);
                                        return notification;
                                    }));
                });
    }

    @Override
    public Mono<Notification> save(Notification notification) {
        notification.setRead(false);
        return notificationPersistencePort.save(notification);
    }

    @Override
    public Mono<Notification> read(String id, Boolean value) {
        return notificationPersistencePort.findById(id)
                .switchIfEmpty(Mono.error(NotificationNotFoundException::new))
                .flatMap(notification -> {
                    notification.setRead(value);
                    return notificationPersistencePort.save(notification);
                });
    }
}
