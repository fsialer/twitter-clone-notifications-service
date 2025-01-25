package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.request;

import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.TargetResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.UserResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNotificationRequest {
    private Long userId;
    private String targetId;
    private String targetType;
}
