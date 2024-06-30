package com.application.puppyWalk.application;

import com.application.puppyWalk.domain.ApplyHistory;
import com.application.puppyWalk.domain.Event;
import com.application.puppyWalk.domain.repository.ApplyHistoryRepository;
import com.application.puppyWalk.domain.repository.EventRepository;
import com.application.puppyWalk.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.desktop.UserSessionEvent;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ApplyHistoryRepository applyHistoryRepository;
    private final DateTimeProvider dateTimeProvider;

    private static final int MAX_PARTICIPANTS = 30;

public EventService(EventRepository eventRepository, UserRepository userRepository, ApplyHistoryRepository applyHistoryRepository, DateTimeProvider dateTimeProvider) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.applyHistoryRepository = applyHistoryRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public List<Event> getEvents() {
        return null;
    }

    public UserEvent applyEvent(final UserSessionEvent userEvent) {
        final User user = userRepository.getUserById(userEvent.getUserId());
        final Event event = eventRepository.getEventById(userEvent.getEventId());
        final LocalDateTime now = dateTimeProvider.getNow();

        isApplyEventValid(user, event, now);

        eventRepository.updateEvent(event);
        applyHistoryRepository.saveApplyHistory(new ApplyHistory(user.getId(), event.getId()));
        userEventRepository.saveUserEvent(new UserEvent(user.getId(), event.getId(), now));

        return convertToUserEvent(user, event);
    }

    private void isApplyEventValid(final User user, final Event event, final LocalDateTime now) {

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
}

