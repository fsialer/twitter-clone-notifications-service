package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.TestUtils.TestUtilsUser;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.UserWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.UserRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRestClientAdapterTest {
    @Mock
    private UserWebClient userWebClient;
    @Mock
    private UserRestClientMapper userRestClientMapper;

    @InjectMocks
    private UserRestClientAdapter userRestClientAdapter;

    @Test
    @DisplayName("When UserId Is Correct Expect User Information Correct")
    void When_UserIdIsCorrect_Expect_UserInformationCorrect(){
        UserResponse userResponse= TestUtilsUser.buildUserResponseMock();
        User user= TestUtilsUser.buildUserMock();
        when(userWebClient.findById(anyLong())).thenReturn(Mono.just(userResponse));
        when(userRestClientMapper.toUser(any(UserResponse.class))).thenReturn(user);
        Mono<User> userInfo=userRestClientAdapter.findById(1L);

        StepVerifier.create(userInfo)
                .expectNext(user)
                .verifyComplete();

        Mockito.verify(userWebClient,times(1)).findById(anyLong());
        Mockito.verify(userRestClientMapper,times(1)).toUser(any(UserResponse.class));
    }
}
