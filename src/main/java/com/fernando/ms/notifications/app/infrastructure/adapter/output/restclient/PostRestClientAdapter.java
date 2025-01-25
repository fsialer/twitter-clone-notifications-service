package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.application.ports.output.ExternalPostOutputPort;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.PostRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.UserRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class PostRestClientAdapter implements ExternalPostOutputPort {
    private final WebClient webClientPost;
    private final PostRestClientMapper postRestClientMapper;
    @Override
    public Mono<Target> findById(String id) {
        return webClientPost
                .get()
                .uri("/posts/{id}",id)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .flatMap(post->{
                    return Mono.just(postRestClientMapper.toTarget(post));
                });
    }
}
