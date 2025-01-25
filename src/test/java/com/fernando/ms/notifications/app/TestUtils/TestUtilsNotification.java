package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.TargetResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.UserResponse;

import java.time.LocalDateTime;

public class TestUtilsNotification {

    public static Notification buildNotificationMock(){
        return Notification.builder()
                .id("dsdsad564sa5d41sa5d5")
                .target(Target.builder()
                        .id("dsd54526545a6d5sad6a")
                        .type("COMMENT")
                        .content("comment")
                        .build())
                .user(User.builder()
                        .id(1L)
                        .names("Fernando")
                        .build())
                .dateNotified(LocalDateTime.now())
                .build();
    }


    public static NotificationResponse buildNotificationResponseMock(){
        return NotificationResponse.builder()
                .id("ss5f45dsf45df5ds4f5")
                .read(false)
                .user(UserResponse.builder()
                        .id(1L)
                        .names("Fernando")
                        .build())
                .dateNotified(LocalDateTime.now())
                .target(
                        TargetResponse.builder().id("dssdsa545d415")
                                .type("COMMENT")
                                .content("content")
                                .build()
                )
                .build();
    }
}
