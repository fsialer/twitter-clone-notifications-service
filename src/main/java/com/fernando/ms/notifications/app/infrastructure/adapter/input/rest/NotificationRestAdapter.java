package com.fernando.ms.notifications.app.infrastructure.adapter.input.rest;

import com.fernando.ms.notifications.app.application.ports.input.NotificationInputPort;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.mapper.NotificationRestMapper;
import com.fernando.ms.notifications.app.infrastructure.adapter.input.rest.models.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
