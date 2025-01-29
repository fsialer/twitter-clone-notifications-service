package com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence;


import com.fernando.ms.notifications.app.TestUtils.TestUtilsNotification;
import com.fernando.ms.notifications.app.domain.models.Notification;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.mapper.NotificationPersistenceMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.models.NotificationDocument;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.persistence.repository.NotificationReactiveMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationPersistenceAdapterTest {
    @Mock
    private NotificationReactiveMongoRepository notificationReactiveMongoRepository;

    @Mock
    private NotificationPersistenceMapper notificationPersistenceMapper;

    @InjectMocks
    private NotificationPersistenceAdapter notificationPersistenceAdapter;


    @Test
    @DisplayName("When UserIdentifierExists Expect A List Notifications Successfully")
    void When_UserIdentifierExists_Expect_AListNotificationsSuccessfully() {
        Notification notification= TestUtilsNotification.buildNotificationMock();
        when(notificationReactiveMongoRepository.findAllByUserIdPaginated(anyLong(), anyLong(), anyLong()))
                .thenReturn(Flux.just(new NotificationDocument())); // Mock the repository response
        when(notificationPersistenceMapper.toNotifications(any(Flux.class)))
                .thenReturn(Flux.just(notification)); // Mock the mapper response

        Flux<Notification> result = notificationPersistenceAdapter.findAllByUser(1L, 0L, 10L);

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }

    @Test
    @DisplayName("When NotificationInformation Is Correct Expect Notification Information Save Correctly")
    void When_NotificationInformationIsCorrect_Expect_NotificationInformationSaveCorrectly() {
        NotificationDocument notificationDocument=TestUtilsNotification.buildNotificationDocumentMock();
        Notification notification= TestUtilsNotification.buildNotificationMock();
        when(notificationPersistenceMapper.toNotificationDocument(any(Notification.class)))
                .thenReturn(notificationDocument);
        when(notificationReactiveMongoRepository.save(any(NotificationDocument.class)))
                .thenReturn(Mono.just(notificationDocument));
        when(notificationPersistenceMapper.toNotification(any(Mono.class)))
                .thenReturn(Mono.just(notification));

        Mono<Notification> result = notificationPersistenceAdapter.save(notification);

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }

    @Test
    @DisplayName("When Notification Identifier Exists Expect Notification Information Correct")
    void When_NotificationIdentifierExists_Expect_NotificationInformationCorrect() {
        NotificationDocument notificationDocument=TestUtilsNotification.buildNotificationDocumentMock();
        Notification notification=TestUtilsNotification.buildNotificationMock();
        when(notificationReactiveMongoRepository.findById(anyString()))
                .thenReturn(Mono.just(notificationDocument));
        when(notificationPersistenceMapper.toNotification(any(NotificationDocument.class)))
                .thenReturn(notification);

        Mono<Notification> result = notificationPersistenceAdapter.findById("notificationId");

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }

    @Test
    @DisplayName("When Notification Information Is Correct Expect Notification Information Saved Correctly")
    void When_NotificationInformationIsCorrect_Expect_NotificationInformationSavedCorrectly() {
        NotificationDocument notificationDocument=TestUtilsNotification.buildNotificationDocumentMock();
        Notification notification=TestUtilsNotification.buildNotificationMock();
        when(notificationPersistenceMapper.toNotificationDocument(any(Notification.class)))
                .thenReturn(notificationDocument);
        when(notificationReactiveMongoRepository.save(any(NotificationDocument.class)))
                .thenReturn(Mono.just(notificationDocument));
        when(notificationPersistenceMapper.toNotification(any(NotificationDocument.class)))
                .thenReturn(notification);

        Mono<Notification> result = notificationPersistenceAdapter.update(notification);

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
    }


    @Test
    @DisplayName("When List Notifications Are Corrects Expect Saved Successfully")
    void When_ListNotificationsAreCorrects_ExpectSavedSuccessfully() {
        NotificationDocument notificationDocument = TestUtilsNotification.buildNotificationDocumentMock();
        Notification notification = TestUtilsNotification.buildNotificationMock();

        when(notificationPersistenceMapper.toNotificationsDocument(any(Iterable.class)))
                .thenReturn(Flux.just(notificationDocument));
        when(notificationReactiveMongoRepository.saveAll(any(Flux.class)))
                .thenReturn(Flux.fromIterable(List.of(notificationDocument)));
        when(notificationPersistenceMapper.toNotifications(any(Flux.class)))
                .thenReturn(Flux.fromIterable(List.of(notification)));

        Flux<Notification> result = notificationPersistenceAdapter.save(List.of(notification));

        StepVerifier.create(result)
                .expectNext(notification)
                .verifyComplete();
        Mockito.verify(notificationPersistenceMapper,times(1)).toNotificationsDocument(any(Iterable.class));
        Mockito.verify(notificationReactiveMongoRepository,times(1)).saveAll(any(Flux.class));
        Mockito.verify(notificationPersistenceMapper,times(1)).toNotifications(any(Flux.class));
    }

}
