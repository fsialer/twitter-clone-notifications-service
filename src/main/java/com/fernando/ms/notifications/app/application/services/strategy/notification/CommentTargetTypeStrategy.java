package com.fernando.ms.notifications.app.application.services.strategy.notification;

import com.fernando.ms.notifications.app.application.ports.output.ExternalCommentOutputPort;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommentTargetTypeStrategy implements ITargetStrategy{
    private final ExternalCommentOutputPort externalCommentOutputPort;

    @Override
    public Mono<Target> doOperation(Target target) {
        return externalCommentOutputPort.findById(target.getId())
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
        return "COMMENT".equalsIgnoreCase(targetType);
    }
}
