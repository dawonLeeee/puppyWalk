package com.application.puppyWalk.controller.dto;

public record FindEventResponse(Long eventId, String eventName) {
    public static FindEventResponse of(Long eventId, String eventName) {
        return new FindEventResponse(eventId, eventName);
    }
}
