package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.impl;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.CommentWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommentWebClientImpl implements CommentWebClient {

    private final WebClient webClientComment;

    @Override
    public Mono<CommentResponse> findById(String id) {
        return webClientComment
                .get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(CommentResponse .class);
    }
}
