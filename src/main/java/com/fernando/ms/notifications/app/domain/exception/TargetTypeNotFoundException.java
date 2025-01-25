package com.fernando.ms.notifications.app.domain.exception;

public class TargetTypeNotFoundException extends RuntimeException{
    public TargetTypeNotFoundException(String msg){
        super(msg);
    }
}
