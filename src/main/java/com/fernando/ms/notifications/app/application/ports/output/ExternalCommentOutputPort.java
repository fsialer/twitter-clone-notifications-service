package com.fernando.ms.notifications.app.application.ports.output;

import com.fernando.ms.notifications.app.domain.models.Target;
import reactor.core.publisher.Mono;

public interface ExternalCommentOutputPort {
    Mono<Target> findById(String id);
}
