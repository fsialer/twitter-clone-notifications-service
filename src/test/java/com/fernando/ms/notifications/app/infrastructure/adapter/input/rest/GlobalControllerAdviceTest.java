package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsNotification;
import com.fernando.ms.notifications.app.application.ports.input.NotificationInputPort;
import com.fernando.ms.notifications.app.domain.exception.NotificationNotFoundException;
import com.fernando.ms.notifications.app.domain.exception.TargetTypeNotFoundException;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper.NotificationRestMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.ErrorResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.enums.ErrorType.SYSTEM;

import static com.fernando.ms.notifications.app.infrastructure.utils.ErrorCatalog.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = {NotificationRestAdapter.class})
public class GlobalControllerAdviceTest {
    @MockBean
    private NotificationRestMapper notificationRestMapper;

    @MockBean
    private NotificationInputPort notificationInputPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DisplayName("Expect TargetTypeNotFoundException When Target Type No Exists")
    void Expect_TargetTypeNotFoundException_When_TargetTypeNoExists() throws Exception {
        //NotificationResponse notificationResponse= TestUtilsNotification.buildNotificationResponseMock();
        //when(notificationRestMapper.toNotificationsResponse(any(Flux.class))).thenReturn(Flux.empty());
        when(notificationInputPort.findAllByUser(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Flux.error(new TargetTypeNotFoundException("Target type not found")));

        webTestClient.get()
                .uri("/notifications/user?userId=1&size=10&page=0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponse.class)
                 .value(response -> {
            assert response.getCode().equals(TARGET_TYPE_NOT_FOUND.getCode());
            assert response.getType().equals(FUNCTIONAL);
            assert response.getMessage().equals(TARGET_TYPE_NOT_FOUND.getMessage());
        });
    }

    @Test
    @DisplayName("Expect RuntimeException When Occurs Exception")
    void Expect_RuntimeException_When_OccursException() {
        when(notificationInputPort.findAllByUser(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenThrow(new RuntimeException("Internal server error"));

        webTestClient.get()
                .uri("/notifications/user?userId=1&size=10&page=0")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(ErrorResponse.class)
                .value(response -> {
                    assert response.getCode().equals(NOTIFICATION_INTERNAL_SERVER_ERROR.getCode());
                    assert response.getType().equals(SYSTEM);
                    assert response.getMessage().equals(NOTIFICATION_INTERNAL_SERVER_ERROR.getMessage());
                });
    }

    @Test
    @DisplayName("Expect NotificationNotFoundException When Notification Not Found")
    void Expect_NotificationNotFoundException_When_NotificationNotFound() {
        NotificationResponse notificationResponse=TestUtilsNotification.buildNotificationResponseMock();
        when(notificationRestMapper.toNotificationResponse(any(Notification.class)))
                .thenReturn(null);
        when(notificationInputPort.read("nonExistentId",true))
                .thenReturn(Mono.error(new NotificationNotFoundException()));

        webTestClient.put()
                .uri("/notifications/{notificationId}/read/{value}", "nonExistentId",true)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorResponse.class)
                .value(response -> {
                    assert response.getCode().equals(NOTIFICATION_NOT_FOUND.getCode());
                    assert response.getType().equals(FUNCTIONAL);
                    assert response.getMessage().equals(NOTIFICATION_NOT_FOUND.getMessage());
                });
    }

}
