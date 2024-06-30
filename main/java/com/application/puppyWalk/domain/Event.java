package com.application.puppyWalk.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class Event {

    private long id;
    private String eventName;
    private LocalDateTime applyDate;
    private LocalDateTime openDate;
    private int appliedCount;

    public Event(final long id, final String eventName, final LocalDateTime applyDate, final LocalDateTime openDate, final int appliedCount) {

        Assert.isTrue(id > 0, "id must be greater than 0");
        Assert.hasText(eventName, "eventName must not be empty");
        Assert.notNull(applyDate, "applyDate must not be null");
        Assert.notNull(openDate, "openDate must not be null");
        Assert.isTrue(appliedCount >= 0, "appliedCount must be greater than or equal to 0");

        this.id = id;
        this.eventName = eventName;
        this.applyDate = applyDate;
        this.openDate = openDate;
        this.appliedCount = appliedCount;
    }

    public void increaseAppliedCount() {
        this.appliedCount++;
    }
}
