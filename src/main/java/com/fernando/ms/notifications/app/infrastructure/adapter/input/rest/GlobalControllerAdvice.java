package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest;

import com.fernando.ms.notifications.app.domain.exception.TargetTypeNotFoundException;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;

import static com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.enums.ErrorType.SYSTEM;
import static com.fernando.ms.notifications.app.infrastructure.utils.ErrorCatalog.*;

public class GlobalControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TargetTypeNotFoundException.class)
    public Mono<ErrorResponse> handleTargetTypeNotFoundException(TargetTypeNotFoundException e) {
        return Mono.just(ErrorResponse.builder()
                .code(TARGET_TYPE_NOT_FOUND.getCode())
                .type(FUNCTIONAL)
                .message(TARGET_TYPE_NOT_FOUND.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Mono<ErrorResponse> handleException(Exception e) {

        return Mono.just(ErrorResponse.builder()
                .code(NOTIFICATION_INTERNAL_SERVER_ERROR.getCode())
                .type(SYSTEM)
                .message(NOTIFICATION_INTERNAL_SERVER_ERROR.getMessage())
                .details(Collections.singletonList(e.getMessage()))
                .timestamp(LocalDate.now().toString())
                .build());
    }
}
