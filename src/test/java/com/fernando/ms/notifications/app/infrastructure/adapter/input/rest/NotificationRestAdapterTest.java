package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest;

import com.fernando.ms.notifications.app.TestUtils.TestUtilsNotification;
import com.fernando.ms.notifications.app.application.ports.input.NotificationInputPort;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper.NotificationRestMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = {NotificationRestAdapter.class})
public class NotificationRestAdapterTest {
    @MockBean
    private NotificationInputPort notificationInputPort;

    @MockBean
    private NotificationRestMapper notificationRestMapper;

    @Autowired
    private WebTestClient webTestClient;



    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("When User Identifier Is Correct Expect A List Notifications Corrects")
    void When_UserIdentifierIsCorrect_Expect_AListNotificationsCorrects() {
        NotificationResponse notificationResponse= TestUtilsNotification.buildNotificationResponseMock();
        when(notificationInputPort.findAllByUser(anyLong(), anyLong(), anyLong()))
                .thenReturn(Flux.just(new Notification()));
        when(notificationRestMapper.toNotificationsResponse(Mockito.any(Flux.class)))
                .thenReturn(Flux.just(notificationResponse));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/notifications/user")
                        .queryParam("userId", 1L)
                        .queryParam("size", 10L)
                        .queryParam("page", 0L)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NotificationResponse.class)
                .value(response -> {
                    assert response.size() == 1;
                    assert response.get(0).getId().equals(notificationResponse.getId());
                    // Add more assertions as needed
                });
    }
}
