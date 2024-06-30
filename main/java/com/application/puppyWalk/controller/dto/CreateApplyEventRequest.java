package com.application.puppyWalk.controller.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateApplyEventRequest {

    private long userId;
    private long eventId;

    public CreateApplyEventRequest(long userId, long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
