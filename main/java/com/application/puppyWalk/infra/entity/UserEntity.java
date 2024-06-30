package com.application.puppyWalk.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEventEntity> userEvents = new ArrayList<>();

    protected UserEntity() {
    }

    public UserEntity(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}