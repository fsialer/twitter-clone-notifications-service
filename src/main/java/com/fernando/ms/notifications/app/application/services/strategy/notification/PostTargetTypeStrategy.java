package com.fernando.ms.notifications.app.application.services.strategy.notification;

import com.fernando.ms.notifications.app.application.ports.output.ExternalPostOutputPort;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostTargetTypeStrategy implements ITargetStrategy{
    private final ExternalPostOutputPort externalPostOutputPort;

    @Override
    public Mono<Target> doOperation(Target target) {
        return externalPostOutputPort.findById(target.getId())
                .flatMap(
                        notificationInfo->{
                            return Mono.just(Target.builder()
                                    .id(notificationInfo.getId())
                                    .type(notificationInfo.getType())
                                    .content(notificationInfo.getContent())
                                    .build());
                        }
                );
    }

    @Override
    public boolean isApplicable(String targetType) {
        return "POST".equalsIgnoreCase(targetType);
    }
}
