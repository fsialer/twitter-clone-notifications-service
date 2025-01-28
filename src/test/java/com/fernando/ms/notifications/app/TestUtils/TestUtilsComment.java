package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;

public class TestUtilsComment {
    public static CommentResponse buildCommentResponseMock(){
        return CommentResponse.builder()
                .id("96d455sd5s4d85sdsd")
                .content("commet")
                .build();
    }
}
