package com.application.puppyWalk.controller.dto;

import com.application.puppyWalk.domain.UserEvent;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetAppliedEventsResponse {

    private long userId;
    private long eventId;
    private String eventName;
    private LocalDateTime openDate;

    public GetAppliedEventsResponse(
            final long userId,
            final long eventId,
            final String eventName,
            final LocalDateTime openDate
    ) {
        this.userId = userId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.openDate = openDate;
    }

    public static List<GetAppliedEventsResponse> from(List<UserEvent> userEvents) {
        return userEvents.stream()
                .map(userEvent -> new GetAppliedEventsResponse(
                        userEvent.getUserId(),
                        userEvent.getEventId(),
                        userEvent.getEventName(),
                        userEvent.getOpenDate()
                ))
                .toList();
    }
}
