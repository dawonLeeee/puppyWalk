package com.application.puppyWalk.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Entity
@Table(name = "user_event")
public class UserEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    private LocalDateTime createdDate;

    protected UserEventEntity() {
    }

    public UserEventEntity(final UserEntity user, final EventEntity event, final LocalDateTime appliedDate) {
        this.user = user;
        this.event = event;
        this.createdDate = appliedDate;

        if (user != null && !user.getUserEvents().contains(this)) {
            user.getUserEvents().add(this);
        }

        if (event != null && !event.getUserEvents().contains(this)) {
            event.getUserEvents().add(this);
        }
    }

}