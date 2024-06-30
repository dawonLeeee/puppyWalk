package com.application.puppyWalk.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String eventName;
    private LocalDateTime applyDate;
    private LocalDateTime openDate;
    private int appliedCount;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEventEntity> userEvents = new ArrayList<>();
    protected EventEntity() {
    }

    public EventEntity(final String eventName, final LocalDateTime applyDate, final LocalDateTime openDate, final int appliedCount) {
        this.eventName = eventName;
        this.applyDate = applyDate;
        this.openDate = openDate;
        this.appliedCount = appliedCount;
    }

    public EventEntity(final int id, final String eventName, final LocalDateTime applyDate, final LocalDateTime openDate, int appliedCount) {
        this.id = id;
        this.eventName = eventName;
        this.applyDate = applyDate;
        this.openDate = openDate;
        this.appliedCount = appliedCount;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", applyDate=" + applyDate +
                ", openDate=" + openDate +
                ", appliedCount=" + appliedCount +
                ", userEvents=" + userEvents +
                '}';
    }

}
