package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;

@Mapper(componentModel = "spring")
public interface NotificationRestMapper {
    default Flux<NotificationResponse> toNotificationsResponse(Flux<Notification> notifications){
        return notifications.map(this::toNotificationResponse);
    }

    NotificationResponse toNotificationResponse(Notification notification);
}
