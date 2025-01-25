package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    private String id;
    private String content;
    private LocalDateTime datePost;
}
