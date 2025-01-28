package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;

public class TestUtilsPost {
    public static PostResponse buildPostResponseMock(){
        return PostResponse.builder()
                .id("96d455sd5s4d85sdsd")
                .content("commet")
                .build();
    }
}
