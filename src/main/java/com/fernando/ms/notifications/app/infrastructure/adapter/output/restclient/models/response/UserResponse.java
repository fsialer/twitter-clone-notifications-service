package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String names;
}
