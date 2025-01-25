package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.domain.models.User;

public class TestUtilsUser {

    public static User buildUserMock(){
        return User.builder()
                .id(1L)
                .names("Fernando")
                .build();
    }
}
