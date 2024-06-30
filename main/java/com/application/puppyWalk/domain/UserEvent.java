package com.application.puppyWalk.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserEvent {

    private long userId;
    private long eventId;
    private String eventName;
    private LocalDateTime openDate;

    public UserEvent(final long userId, final long eventId) {
        Assert.isTrue(userId > 0, "userId must be greater than 0");
        Assert.isTrue(eventId > 0, "eventId must be greater than 0");

        this.userId = userId;
        this.eventId = eventId;
    }

    @Builder
    public UserEvent(final long userId, final long eventId, final String eventName, final LocalDateTime openDate) {
        Assert.isTrue(userId > 0, "userId must be greater than 0");
        Assert.isTrue(eventId > 0, "eventId must be greater than 0");
        Assert.hasText(eventName, "eventName must not be empty");
        Assert.notNull(openDate, "openDate must not be null");

        this.userId = userId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.openDate = openDate;
    }

    public UserEvent(long id, long id1, LocalDateTime now) {
    }
}
