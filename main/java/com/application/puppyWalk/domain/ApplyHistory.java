package com.application.puppyWalk.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
public class ApplyHistory {

    private long id;
    private long userId;
    private long eventId;

    public ApplyHistory(final long userId, final long eventId) {

        Assert.isTrue(userId > 0, "id must be greater than 0");
        Assert.isTrue(eventId > 0, "eventId must be greater than 0");

        this.userId = userId;
        this.eventId = eventId;
    }

    @Builder
    public ApplyHistory(final long id, final long userId, final long eventId) {

        Assert.isTrue(id > 0, "id must be greater than 0");
        Assert.isTrue(eventId > 0, "eventId must be greater than 0");
        Assert.isTrue(userId > 0, "userId must be greater than 0");

        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }


}
