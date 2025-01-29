package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.impl;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.UserWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserWebClientImpl implements UserWebClient {
    private final WebClient webClientUser;
    @Override
    public Mono<UserResponse> findById(Long id) {
        return webClientUser
                .get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(UserResponse.class);

    }
}
