package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import reactor.core.publisher.Mono;

public interface CommentWebClient {
    Mono<CommentResponse> findById(String id);
}
