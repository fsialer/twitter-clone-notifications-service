package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import reactor.core.publisher.Mono;

public interface UserWebClient {
    Mono<UserResponse> findById(Long id);
}
