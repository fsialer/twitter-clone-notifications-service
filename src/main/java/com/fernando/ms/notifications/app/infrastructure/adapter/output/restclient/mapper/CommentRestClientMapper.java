package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentRestClientMapper {

    default Target toTarget(CommentResponse comment){
        return Target.builder()
                .id(comment.getId())
                .type("COMMENT")
                .content(comment.getContent())
                .build();
    }
}
