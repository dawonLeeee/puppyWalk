package com.application.puppyWalk.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@ToString
public class User {

    private long id;
    private String name;

    @Builder
    public User(final long id, final String name) {
        Assert.isTrue(id > 0, "id must be greater than 0");
        Assert.hasText(name, "name must not be empty");

        this.id = id;
        this.name = name;
    }
}
