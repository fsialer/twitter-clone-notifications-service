package com.fernando.ms.notifications.app.TestUtils;

import com.fernando.ms.notifications.app.domain.models.Target;

public class TestUtilsTarget {
    public static Target buildTargetMock(){
        return Target.builder()
                .id("96d455sd5s4d85sdsd")
                .type("COMMENT")
                .content("commet")
                .build();
    }
}
