package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

@Mapper(componentModel = "spring")
public interface UserRestClientMapper {

    User toUser(UserResponse user);


}
