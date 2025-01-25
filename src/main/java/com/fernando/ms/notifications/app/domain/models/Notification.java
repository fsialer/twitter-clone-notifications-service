package com.fernando.ms.notifications.app.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private String id;
    private User user;
    private Target target;
    private Boolean read;
    private LocalDateTime dateNotified;
}
