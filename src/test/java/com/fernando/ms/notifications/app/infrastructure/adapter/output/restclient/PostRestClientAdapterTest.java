package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.TestUtils.TestUtilsPost;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsTarget;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsUser;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.domain.models.User;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.PostWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.UserWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.PostRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.UserRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostRestClientAdapterTest {
    @Mock
    private PostWebClient postWebClient;
    @Mock
    private PostRestClientMapper postRestClientMapper;

    @InjectMocks
    private PostRestClientAdapter postRestClientAdapter;

    @Test
    @DisplayName("When PostId Is Correct Expect Post Information Correct")
    void When_PostIdIsCorrect_Expect_PostInformationCorrect(){
        PostResponse postResponse= TestUtilsPost.buildPostResponseMock();
        Target target= TestUtilsTarget.buildTargetMock();
        when(postWebClient.findById(anyString())).thenReturn(Mono.just(postResponse));
        when(postRestClientMapper.toTarget(any(PostResponse.class))).thenReturn(target);
        Mono<Target> postInfo=postRestClientAdapter.findById("54847sadwwv645456");

        StepVerifier.create(postInfo)
                .expectNext(target)
                .verifyComplete();

        Mockito.verify(postWebClient,times(1)).findById(anyString());
        Mockito.verify(postRestClientMapper,times(1)).toTarget(any(PostResponse.class));
    }
}
