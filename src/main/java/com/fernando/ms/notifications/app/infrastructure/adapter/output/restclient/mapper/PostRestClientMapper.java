package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostRestClientMapper {
    default Target toTarget(PostResponse post){
        return Target.builder()
                .id(post.getId())
                .type("POST")
                .content(post.getContent())
                .build();
    }


}
