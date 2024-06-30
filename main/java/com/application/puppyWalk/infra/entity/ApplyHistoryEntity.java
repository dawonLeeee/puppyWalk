package com.application.puppyWalk.infra.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
@Table(name = "apply_history")
public class ApplyHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long eventId;
    protected ApplyHistoryEntity() {
    }

    @Builder
    public ApplyHistoryEntity(long userId, long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
