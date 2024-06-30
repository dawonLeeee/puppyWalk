package com.application.puppyWalk.application;

import com.application.puppyWalk.domain.ApplyHistory;
import com.application.puppyWalk.domain.DateTimeProvider;
import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.UserEvent;
import com.application.puppyWalk.domain.repository.ApplyHistoryRepository;
import com.application.puppyWalk.domain.repository.EventRepository;
import com.application.puppyWalk.domain.repository.UserEventRepository;
import com.application.puppyWalk.domain.repository.UserRepository;
import com.application.puppyWalk.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;
    private final ApplyHistoryRepository applyHistoryRepository;
    private final DateTimeProvider dateTimeProvider;

    private static final int MAX_PARTICIPANTS = 30;

public EventService(EventRepository eventRepository, UserRepository userRepository, UserEventRepository userEventRepository, ApplyHistoryRepository applyHistoryRepository, DateTimeProvider dateTimeProvider) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.userEventRepository = userEventRepository;
        this.applyHistoryRepository = applyHistoryRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public List<Event> getEvents() {
        return null;
    }

    public UserEvent applyEvent(final UserEvent userEvent) {
        final User user = userRepository.getUserById(userEvent.getUserId());
        final Event event = eventRepository.getEventById(userEvent.getEventId());
        final LocalDateTime now = dateTimeProvider.now();

        isApplyEventValid(now, user, event);

        eventRepository.updateEvent(event);
        applyHistoryRepository.saveApplyHistory(new ApplyHistory(user.getId(), event.getId()));
        userEventRepository.saveUserEvent(user, event, now);

        return convertToUserEvent(user, event);
    }



    private void isApplyEventValid(final LocalDateTime now, final User user, final Event event) {

        if (event.getApplyDate().isBefore(now)) {
            throw new IllegalArgumentException("Event is already closed");
        }

        if (event.getAppliedCount() >= MAX_PARTICIPANTS) {
            throw new IllegalArgumentException("Event is already full");
        }

        if (applyHistoryRepository.existsApplyHistory(user.getId(), event.getId())) {
            throw new IllegalArgumentException("User already applied");
        }
    }

    private UserEvent convertToUserEvent(User user, Event event) {
        return UserEvent.builder()
                .userId(user.getId())
                .eventId(event.getId())
                .eventName(event.getEventName())
                .openDate(event.getOpenDate())
                .build();
    }

    @Transactional(readOnly = true)
    public List<UserEvent> getAppliedEvents(long userId) {
        final User user = userRepository.getUserById(userId);
        final LocalDateTime now = dateTimeProvider.now();

        final List<UserEvent> events = userEventRepository.getAppliedEvents(user, now);

        return events;
    }
}

