package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.application.ports.output.ExternalCommentOutputPort;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.CommentRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.PostRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CommentRestClientAdapter implements ExternalCommentOutputPort {
    private final WebClient webClientComment;
    private final CommentRestClientMapper commentRestClientMapper;

    @Override
    public Mono<Target> findById(String id) {
        return webClientComment
                .get()
                .uri("/comments/{id}",id)
                .retrieve()
                .bodyToMono(CommentResponse.class)
                .flatMap(comment->{
                    return Mono.just(commentRestClientMapper.toTarget(comment));
                });
    }
}
