package com.application.puppyWalk.controller.dto;

import com.application.puppyWalk.domain.Event;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetEventsResponse {

    private long id;
    private String eventName;
    private LocalDateTime applyDate;
    private LocalDateTime openDate;
    private int appliedCount;

    public GetEventsResponse(long id, String eventName, LocalDateTime applyDate, LocalDateTime openDate, int appliedCount) {
        this.id = id;
        this.eventName = eventName;
        this.applyDate = applyDate;
        this.openDate = openDate;
        this.appliedCount = appliedCount;
    }

    public static List<GetEventsResponse> from(List<Event> events) {
        return events.stream()
                .map(event -> new GetEventsResponse(
                        event.getId(),
                        event.getEventName(),
                        event.getApplyDate(),
                        event.getOpenDate(),
                        event.getAppliedCount()
                ))
                .toList();
    }
}
