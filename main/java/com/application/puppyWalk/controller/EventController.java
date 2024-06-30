package com.application.puppyWalk.controller;

import com.application.puppyWalk.common.ApiResponse;
import com.application.puppyWalk.controller.dto.GetEventsResponse;
import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
