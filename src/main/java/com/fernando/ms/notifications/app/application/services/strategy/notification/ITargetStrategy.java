package com.fernando.ms.notifications.app.application.services.strategy.notification;

import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import reactor.core.publisher.Mono;

public interface ITargetStrategy {
    Mono<Target> doOperation(Target target);
    boolean isApplicable(String targetType);
}
