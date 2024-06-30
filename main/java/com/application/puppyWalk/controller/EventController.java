package com.application.puppyWalk.controller;

import com.application.puppyWalk.application.EventService;
import com.application.puppyWalk.common.ApiResponse;
import com.application.puppyWalk.controller.dto.CreateApplyEventRequest;
import com.application.puppyWalk.controller.dto.CreateApplyEventResponse;
import com.application.puppyWalk.controller.dto.GetAppliedEventsResponse;
import com.application.puppyWalk.controller.dto.GetEventsResponse;
import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    /*
    * 이벤트 목록
    */
    @GetMapping
    public ApiResponse<List<GetEventsResponse>> getEvents() {
        final List<Event> events = eventService.getEvents();
        return ApiResponse.isOk(GetEventsResponse.from(events));
    }

    @PostMapping("/apply")
    public ApiResponse<CreateApplyEventResponse> applyEventResponseApiResponse(
            @RequestBody final CreateApplyEventRequest request
    ) {
        final UserEvent userEvent = eventService.applyEvent(new UserEvent(request.getUserId(), request.getEventId()));
        return ApiResponse.isOk(CreateApplyEventResponse.from(userEvent));
    }

    @GetMapping("/application/{userId}")
    public ApiResponse<List<GetAppliedEventsResponse>> getApplyEvents(
            @PathVariable final long userId
    ) {
        final List<UserEvent> appliedEvents = eventService.getAppliedEvents(userId);
        return ApiResponse.isOk(GetAppliedEventsResponse.from(appliedEvents));
    }
}
