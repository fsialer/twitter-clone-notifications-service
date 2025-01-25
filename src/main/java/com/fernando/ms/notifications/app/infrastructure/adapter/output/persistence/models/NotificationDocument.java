package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "notifications")
public class NotificationDocument {
    private String id;
    private Long userId;
    private String targetId;
    private String targetType;//POST/COMMENT/FOLLOWER
    private Boolean read;
    private LocalDateTime dateNotified;
}
