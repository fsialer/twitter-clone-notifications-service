package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.request;

import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.TargetResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.UserResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNotificationRequest {
    @NotNull(message = "Field userId cannot be null.")
    private Long userId;
    @NotBlank(message = "Field targetId cannot be null or blank.")
    private String targetId;
    @NotBlank(message = "Field targetType cannot be null or blank.")
    private String targetType;
}
