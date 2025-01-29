package com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient;

import com.fernando.ms.notifications.app.TestUtils.TestUtilsComment;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsPost;
import com.fernando.ms.notifications.app.TestUtils.TestUtilsTarget;
import com.fernando.ms.notifications.app.domain.models.Target;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.CommentWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.client.PostWebClient;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.CommentRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.mapper.PostRestClientMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.CommentResponse;
import com.fernando.ms.notifications.app.infrastructure.adapter.output.restclient.models.response.PostResponse;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentRestClientAdapterTest {
    @Mock
    private CommentWebClient commentWebClient;
    @Mock
    private CommentRestClientMapper commentRestClientMapper;

    @InjectMocks
    private CommentRestClientAdapter commentRestClientAdapter;

    @Test
    @DisplayName("When CommentId Is Correct Expect Comment Information Correct")
    void When_CommentIdIsCorrect_Expect_CommentInformationCorrect(){
        CommentResponse commentResponse= TestUtilsComment.buildCommentResponseMock();
        Target target= TestUtilsTarget.buildTargetMock();
        when(commentWebClient.findById(anyString())).thenReturn(Mono.just(commentResponse));
        when(commentRestClientMapper.toTarget(any(CommentResponse.class))).thenReturn(target);
        Mono<Target> commentInfo=commentRestClientAdapter.findById("54847sadwwv645456");

        StepVerifier.create(commentInfo)
                .expectNext(target)
                .verifyComplete();

        Mockito.verify(commentWebClient,times(1)).findById(anyString());
        Mockito.verify(commentRestClientMapper,times(1)).toTarget(any(CommentResponse.class));
    }

}
