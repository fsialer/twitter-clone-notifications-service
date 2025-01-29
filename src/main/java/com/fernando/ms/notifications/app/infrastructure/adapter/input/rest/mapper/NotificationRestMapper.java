package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.request.CreateNotificationRequest;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Mapper(componentModel = "spring")
public interface NotificationRestMapper {
    default Flux<NotificationResponse> toNotificationsResponse(Flux<Notification> notifications){
        return notifications.map(this::toNotificationResponse);
    }

    NotificationResponse toNotificationResponse(Notification notification);

    @Mapping(target="user", expression = "java(toUser(rq))")
    @Mapping(target="target", expression = "java(toTarget(rq))")
    Notification toNotification(CreateNotificationRequest rq);

    default User toUser(CreateNotificationRequest rq){
        return User.builder().id(rq.getUserId()).build();
    }

    default Target toTarget(CreateNotificationRequest rq){
        return Target.builder().id(rq.getTargetId()).type(rq.getTargetType()).build();
    }

    default List<Notification> toUsers(Iterable<CreateNotificationRequest> rqLst) {
        return StreamSupport.stream(rqLst.spliterator(), false)
                .map(this::toNotification)
                .collect(Collectors.toList());
    }
}
