package com.fernando.ms.notifications.app.infrastructure.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {

    TARGET_TYPE_NOT_FOUND("NOTIFICATION_MS_001", "Target type not found. "),
    NOTIFICATION_NOT_FOUND("NOTIFICATION_MS_002", "Notification not found."),
    NOTIFICATION_BAB_REQUEST("NOTIFICATION_MS_003", "Invalid parameters for creation notification."),
    NOTIFICATION_INTERNAL_SERVER_ERROR("NOTIFICATION_MS_000", "Internal server error.");
    private final String code;
    private final String message;
}
