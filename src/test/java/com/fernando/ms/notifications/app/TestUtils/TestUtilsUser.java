package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;

public class TestUtilsUser {

    public static User buildUserMock(){
        return User.builder()
                .id(1L)
                .names("Fernando")
                .build();
    }

    public static UserResponse buildUserResponseMock(){
        return UserResponse.builder()
                .id(1L)
                .username("falex")
                .names("Fernando")
                .email("example@mail.com")
                .build();
    }
}
