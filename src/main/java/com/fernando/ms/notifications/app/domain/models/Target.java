package com.fernando.ms.notifications.app.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Target {
    private String id;
    private String type;
    private String content;
}
