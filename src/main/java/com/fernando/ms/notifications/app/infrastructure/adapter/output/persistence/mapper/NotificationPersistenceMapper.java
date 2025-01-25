package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.mapper;

import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface NotificationPersistenceMapper {

    default Flux<Notification> toNotifications(Flux<NotificationDocument> notifications){
        return notifications.map(this::toNotification);
    }

    @Mapping(target="user", expression = "java(toUser(notifications))")
    @Mapping(target="target", expression = "java(toTarget(notifications))")
    Notification toNotification(NotificationDocument notifications);

    default User toUser(NotificationDocument notifications){
        return User.builder().id(notifications.getUserId()).build();
    }

    default Target toTarget(NotificationDocument notifications){
        return Target.builder()
                .id(notifications.getTargetId())
                .type(notifications.getTargetType())
                .build();
    }

    default NotificationDocument toNotificationDocument(Notification notification){
        return NotificationDocument
                .builder()
                .dateNotified(LocalDateTime.now())
                .read(notification.getRead())
                .userId(notification.getUser().getId())
                .targetId(notification.getTarget().getId())
                .targetType(notification.getTarget().getType())
                .build();
    }

    default Mono<Notification> toNotification(Mono<NotificationDocument> notification){
        return notification.map(this::toNotification);
    }
}
