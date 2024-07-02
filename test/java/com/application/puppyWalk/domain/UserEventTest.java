package com.application.puppyWalk.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class UserEventTest {

    @Test
    void 유저id는_양수여야한다() {
        final long userId1 = 0L;
        final long userId2 = -1L;
        final long eventId = 1L;
        final String eventTitle = "test";
        final LocalDateTime eventDate = LocalDateTime.now();

        Assertions.assertThatThrownBy(() -> new UserEvent(userId1, eventId, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userId must be greater than 0");

        Assertions.assertThatThrownBy(() -> new UserEvent(userId2, eventId, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("userId must be greater than 0");
    }


    @Test
    void 이벤트id는_양수여야한다() {
        final long userId = 1L;
        final long eventId1 = 0L;
        final long eventId2 = -1L;
        final String eventTitle = "test";
        final LocalDateTime eventDate = LocalDateTime.now();

        Assertions.assertThatThrownBy(() -> new UserEvent(userId, eventId1, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("eventId must be greater than 0");

        Assertions.assertThatThrownBy(() -> new UserEvent(userId, eventId2, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("eventId must be greater than 0");

    }

    @Test
    void 이벤트명은_존재해야한다() {
        final long userId = 1L;
        final long eventId = 1L;
        final String eventTitle = null;
        final LocalDateTime eventDate = LocalDateTime.now();

        Assertions.assertThatThrownBy(() -> new UserEvent(userId, eventId, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("eventTitle is required");
    }

    @Test
    void 이벤트날짜는_존재해야한다() {
        final long userId = 1L;
        final long eventId = 1L;
        final String eventTitle = "test";
        final LocalDateTime eventDate = null;

        Assertions.assertThatThrownBy(() -> new UserEvent(userId, eventId, eventTitle, eventDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("eventDate is required");
    }

}
