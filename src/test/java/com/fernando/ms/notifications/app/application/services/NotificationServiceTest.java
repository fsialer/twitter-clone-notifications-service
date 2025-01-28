package com.fernando.ms.notifications.app.application.services;

import com.fernando.ms.notifications.app.TestUtils.TestUtilsNotification;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsTarget;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsUser;
import com.fernando.ms.notifications.app.application.ports.output.ExternalCommentOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.ExternalPostOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.ExternalUserOutputPort;
import com.fernando.ms.notifications.app.application.ports.output.NotificationPersistencePort;
import com.fernando.ms.notifications.app.application.services.strategy.notification.ITargetStrategy;
import com.fernando.ms.notifications.app.domain.exception.NotificationNotFoundException;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {
    @Mock
    private NotificationPersistencePort notificationPersistencePort;
    @Mock
    private ExternalUserOutputPort externalUserOutputPort;

    @Mock
    private ExternalPostOutputPort externalPostOutputPort;

    @Mock
    private ExternalCommentOutputPort externalCommentOutputPort;

    @Mock
    private List<ITargetStrategy> ITargetStrategyList;

    @Mock
    private ITargetStrategy iTargetStrategy;

    @InjectMocks
    private NotificationService notificationService;


    @BeforeEach
    void setUp() {
        ITargetStrategyList = List.of(iTargetStrategy);
        notificationService=new NotificationService(notificationPersistencePort,externalUserOutputPort,ITargetStrategyList);
    }

    @Test
    @DisplayName("When User Identifier Exists Expect_AListNotifications")
    void When_UserIdentifierExists_Expect_AListNotifications() {
        Notification notification= TestUtilsNotification.buildNotificationMock();
        Target target= TestUtilsTarget.buildTargetMock();
        User user= TestUtilsUser.buildUserMock();

        when(iTargetStrategy.isApplicable(anyString())).thenReturn(true);
        when(iTargetStrategy.doOperation(any(Target.class))).thenReturn(Mono.just(target));
        when(notificationPersistencePort.findAllByUser(anyLong(), anyLong(), anyLong()))
                .thenReturn(Flux.just(notification));
        when(externalUserOutputPort.findById(anyLong()))
                .thenReturn(Mono.just(user));

        Flux<Notification> result = notificationService.findAllByUser(1L, 0L, 10L);

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }

    @Test
    @DisplayName("When Notification Information Is Correct Expect Notification Information To Be Save")
    void When_NotificationInformationIsCorrect_Expect_NotificationInformationToBeSave() {
        Notification notification= TestUtilsNotification.buildNotificationMock();
        when(notificationPersistencePort.save(any(Notification.class)))
                .thenReturn(Mono.just(notification));

        Mono<Notification> result = notificationService.save(notification);

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }

    @Test
    @DisplayName("When Notification Exists Expect Notification To Be Marked As Read")
    void whenNotificationExists_expectNotificationToBeMarkedAsRead() {
        Notification notification= TestUtilsNotification.buildNotificationMock();
        when(notificationPersistencePort.findById(anyString()))
                .thenReturn(Mono.just(notification));
        when(notificationPersistencePort.save(any(Notification.class)))
                .thenReturn(Mono.just(notification));

        Mono<Notification> result = notificationService.read("notificationId", true);

        StepVerifier.create(result)
                .expectNextMatches(savedNotification -> savedNotification.getRead().equals(true))
                .verifyComplete();
    }

    @Test
    @DisplayName("When Notification Does Not Exist Expect NotificationNotFoundException")
    void whenNotificationDoesNotExist_expectNotificationNotFoundException() {
        when(notificationPersistencePort.findById(anyString()))
                .thenReturn(Mono.empty());

        Mono<Notification> result = notificationService.read("notificationId", true);

        StepVerifier.create(result)
                .expectError(NotificationNotFoundException.class)
                .verify();
    }
}
