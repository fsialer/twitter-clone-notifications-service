package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.application.ports.output.ExternalUserOutputPort;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.UserWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.UserRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class UserRestClientAdapter implements ExternalUserOutputPort {
    private final WebClient webClientUser;
    private final UserRestClientMapper userRestClientMapper;
    private final UserWebClient userWebClient;
    @Override
    public Mono<User> findById(Long id) {
        return userWebClient.findById(id)
                .flatMap(user->{
                    return Mono.just(userRestClientMapper.toUser(user));
                });
    }
}
