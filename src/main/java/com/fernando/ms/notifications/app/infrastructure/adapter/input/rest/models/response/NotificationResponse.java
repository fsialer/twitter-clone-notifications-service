package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private String id;
    private UserResponse user;
    private TargetResponse target;
    private Boolean read;
    private LocalDateTime dateNotified;
}
