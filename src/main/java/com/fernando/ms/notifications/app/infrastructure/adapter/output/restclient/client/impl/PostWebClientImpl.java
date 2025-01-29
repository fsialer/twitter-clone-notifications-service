package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.impl;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.PostWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostWebClientImpl implements PostWebClient {
    private final WebClient webClientPost;
    @Override
    public Mono<PostResponse> findById(String id) {
        return webClientPost
                .get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(PostResponse.class);
    }
}
