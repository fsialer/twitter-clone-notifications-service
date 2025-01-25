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
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
}
