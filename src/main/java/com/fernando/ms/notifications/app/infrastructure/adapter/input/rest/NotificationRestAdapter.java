package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest;

import com.fernando.ms.notifications.app.application.ports.input.NotificationInputPort;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper.NotificationRestMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.request.CreateNotificationRequest;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationRestAdapter {
    private final NotificationInputPort notificationInputPort;
    private final NotificationRestMapper notificationRestMapper;

    @GetMapping("/user")
    public Flux<NotificationResponse> findAllByUserId(@RequestParam(name = "userId",required = true) Long userId,
                                                      @RequestParam(name = "size",required = false,defaultValue = "10") Long size,
                                                      @RequestParam(name = "page",required = false,defaultValue = "0") Long page){
        return  notificationRestMapper.toNotificationsResponse(notificationInputPort.findAllByUser(userId,page,size));
    }

    @PostMapping
    public Mono<ResponseEntity<NotificationResponse>> save(@Valid @RequestBody CreateNotificationRequest rq){
        return notificationInputPort.save(notificationRestMapper.toNotification(rq))
                .flatMap(notification -> {
                    String location="/notifications/".concat(notification.getId());
                            return Mono.just(ResponseEntity.created(URI.create(location)).body(notificationRestMapper.toNotificationResponse(notification)));
                });
    }

    @PutMapping("/{notificationId}/read/{value}")
    public Mono<ResponseEntity<NotificationResponse>> read(@PathVariable("notificationId") String notificationId,@PathVariable("value") Boolean value){
        return notificationInputPort.read(notificationId,value)
                .flatMap(notification -> {
                    return Mono.just(ResponseEntity.ok().body(notificationRestMapper.toNotificationResponse(notification)));
                });
    }
}
