package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.application.ports.output.ExternalPostOutputPort;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.PostWebClient;
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
    private final PostRestClientMapper postRestClientMapper;
    private final PostWebClient postWebClient;
    @Override
    public Mono<Target> findById(String id) {
        return postWebClient.findById(id)
                .flatMap(post->{
                    return Mono.just(postRestClientMapper.toTarget(post));
                });
    }
}
