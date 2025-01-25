package com.fernando.ms.notifications.app.application.ports.output;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import reactor.core.publisher.Mono;

public interface ExternalPostOutputPort {
    Mono<Target> findById(String id);
}
