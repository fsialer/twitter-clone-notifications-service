package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TargetResponse {
    private String id;
    private String type;
    private String content;
}
